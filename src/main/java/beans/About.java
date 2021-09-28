
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class About implements Serializable {
    private final static long serialVersionUID = 1652553458039923472L;

    @SerializedName("storageQuota")
    @Expose
    private StorageQuota storageQuota;
    @SerializedName("user")
    @Expose
    private User user;

    public About() {
    }

    public About(StorageQuota storageQuota, User user) {
        super();
        this.storageQuota = storageQuota;
        this.user = user;
    }

    public StorageQuota getStorageQuota() {
        return storageQuota;
    }

    public void setStorageQuota(StorageQuota storageQuota) {
        this.storageQuota = storageQuota;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("storageQuota", storageQuota).append("user", user).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(storageQuota).append(user).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof About) == false) {
            return false;
        }
        About rhs = ((About) other);
        return new EqualsBuilder().append(storageQuota, rhs.storageQuota).append(user, rhs.user).isEquals();
    }

}
