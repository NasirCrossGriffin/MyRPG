package myrpg.backend.api.dto;

public class FollowsResponse {
    private Long userAccountId;
    private Long followerAccountId;

    public FollowsResponse() {
        
    }

    public Long getUserAccountId() {
        return this.userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Long getFollowerAccountId() {
        return this.followerAccountId;
    }

    public void setFollowerAccountId(Long followerAccountId) {
        this.followerAccountId = followerAccountId;
    }
}
