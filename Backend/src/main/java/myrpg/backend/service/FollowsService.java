package myrpg.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import myrpg.backend.api.dto.FollowsRequest;
import myrpg.backend.api.dto.FollowsResponse;
import myrpg.backend.api.dto.UserResponse;
import myrpg.backend.api.model.Follows;
import myrpg.backend.api.model.User;
import myrpg.backend.api.repository.FollowsRepository;
import myrpg.backend.api.repository.UserRepository;

@Service
public class FollowsService {
    private final FollowsRepository followsRepository;
    private final UserRepository userRepository;
    private final UserService userService;



    public FollowsService(FollowsRepository followsRepository, UserRepository userRepository, UserService userService) {
        this.followsRepository = followsRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }
    

    public List<UserResponse> getFollowers(Long userId) {
        List<Follows> followBridges = null;
        try {
            followBridges = followsRepository.getFollowersByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        List<UserResponse> userResponses = new ArrayList<>();
        followBridges.forEach((bridge) -> {
            userResponses.add(userService.getUser(bridge.getFollowerAccount().getId()));
        });

        return userResponses;
    }

    public List<UserResponse> getFollowing(Long userId) {
        List<Follows> followBridges = null;
        try {
            followBridges = followsRepository.getFollowingByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        List<UserResponse> userResponses = new ArrayList<>();
        followBridges.forEach((bridge) -> {
            userResponses.add(userService.getUser(bridge.getUserAccount().getId()));
        });

        return userResponses;
    }

    public int countFollowers(Long userId) {
        return followsRepository.countFollowers(userId);
    }

    public FollowsResponse createFollow(FollowsRequest followsRequest) {
        Follows follow = new Follows();
        Long userId = followsRequest.getUserAccountId();
        Long followerId = followsRequest.getFollowerAccountId();
        User userAccount = null;
        User followerAccount = null;
        try {
            userAccount = userRepository.findById(userId).get();
            System.out.println("Found User With ID: " + userAccount.getId());
            followerAccount = userRepository.findById(followerId).get();
            System.out.println("Found Follower With ID: " + followerAccount.getId());
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        follow.setUserAccount(userAccount);
        follow.setFollowerAccount(followerAccount);

        System.out.println(follow);

        Follows newFollow = followsRepository.save(follow);

        return newFollow.createResponse();
    }

    public boolean unfollow(FollowsRequest followsRequest) {
        Long userId = followsRequest.getFollowerAccountId();
        Long followingId = followsRequest.getUserAccountId();
        try {
            followsRepository.deleteFollowBridge(followingId, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean removeFollower(FollowsRequest followsRequest) {
        Long followerId = followsRequest.getFollowerAccountId();
        Long userId = followsRequest.getUserAccountId();
        try {
            followsRepository.deleteFollowBridge(userId, followerId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public FollowsResponse getFollowsBridge(FollowsRequest followsRequest) {
        Follows followBridge = null;

        try {
            followBridge = followsRepository.getFollowBridge(followsRequest.getUserAccountId(), followsRequest.getFollowerAccountId()).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return followBridge.createResponse();
    }
}
