package myrpg.backend.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myrpg.backend.api.dto.FollowsRequest;
import myrpg.backend.api.dto.FollowsResponse;
import myrpg.backend.api.dto.UserResponse;
import myrpg.backend.api.model.Follows;
import myrpg.backend.api.model.User;
import myrpg.backend.service.FollowsService;

@RestController
public class FollowsController {
    private FollowsService followsService;

    @Autowired
    public FollowsController(FollowsService followsService) {
        this.followsService = followsService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/follows/followers")
    public ResponseEntity<List<UserResponse>> getFollowers(@RequestBody Long userId) {
        List<UserResponse> followers = followsService.getFollowers(userId);
        
        if (followers != null) {
            return ResponseEntity.ok(followers);
        } 
            
        return ResponseEntity.badRequest().body(null);
        
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/follows/following")
    public ResponseEntity<List<UserResponse>> getFollowing(@RequestBody Long userId) {
        List<UserResponse> following = followsService.getFollowing(userId);
        
        if (following != null) {
            return ResponseEntity.ok(following);
        } 
            
        return ResponseEntity.badRequest().body(null);
        
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/follows/count")
    public ResponseEntity<Integer> countFollowers(@RequestBody Long userId) {
        return ResponseEntity.ok(new Integer(followsService.countFollowers(userId)));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/follows/new")
    public ResponseEntity<FollowsResponse> createFollow(@RequestBody FollowsRequest followsRequest) {
        FollowsResponse followBridge = followsService.createFollow(followsRequest);

        if (followBridge != null) {
            return ResponseEntity.ok(followBridge);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/follows/unfollow")
    public ResponseEntity<Boolean> unfollow(@RequestBody FollowsRequest followsRequest) {
        boolean unfollowStatus = followsService.unfollow(followsRequest);

        if (unfollowStatus == true) {
            return ResponseEntity.ok(new Boolean(unfollowStatus));
        }

        return ResponseEntity.badRequest().body(null);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/follows/remove")
    public ResponseEntity<Boolean> removeFollower(@RequestBody FollowsRequest followsRequest) {
        boolean removeFollowerStatus = followsService.removeFollower(followsRequest);

        if (removeFollowerStatus == true) {
            return ResponseEntity.ok(new Boolean(removeFollowerStatus));
        }

        return ResponseEntity.badRequest().body(null);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/follows/get")
    public ResponseEntity<FollowsResponse> getFollowBridge(@RequestBody FollowsRequest followsRequest) {
        FollowsResponse followBridge = followsService.getFollowsBridge(followsRequest);

        if (followBridge != null) {
            return ResponseEntity.ok(followBridge);
        }

        return ResponseEntity.badRequest().body(null);
    }
}
