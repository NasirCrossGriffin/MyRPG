package myrpg.backend.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import myrpg.backend.api.dto.FollowsResponse;

@Entity
@IdClass(FollowsCompositeKey.class)
@Table (
    name = "Follows"
)
public class Follows {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_account", referencedColumnName = "id")
    private User userAccount;

    @Id
    @ManyToOne
    @JoinColumn(name = "follower_account", referencedColumnName = "id")
    private User followerAccount;

    public Follows() {
    }

    public User getUserAccount() {
        return this.userAccount;
    }

    public void setUserAccount(User userAccount) {
        this.userAccount = userAccount;
    }

    public User getFollowerAccount() {
        return this.followerAccount;
    }

    public void setFollowerAccount(User followerAccount) {
        this.followerAccount = followerAccount;
    }

    public FollowsResponse createResponse() {
        FollowsResponse followsResponse = new FollowsResponse();

        followsResponse.setFollowerAccountId(this.followerAccount.getId());
        followsResponse.setUserAccountId(this.userAccount.getId());

        return followsResponse;
    }


    @Override
    public String toString() {
        return "{" +
            " userAccount='" + getUserAccount() + "'" +
            ", followerAccount='" + getFollowerAccount() + "'" +
            "}";
    }

}
    

