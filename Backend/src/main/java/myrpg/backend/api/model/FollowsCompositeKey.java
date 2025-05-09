package myrpg.backend.api.model;

import java.io.Serializable;
import java.util.Objects;

public class FollowsCompositeKey implements Serializable {
    private User userAccount;
    private User followerAccount;

    public FollowsCompositeKey() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowsCompositeKey that = (FollowsCompositeKey) o;
        return Objects.equals(this.userAccount, that.userAccount) &&
               Objects.equals(this.followerAccount, that.followerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userAccount, this.followerAccount);
    }
}
