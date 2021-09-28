
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class LastModifyingUser {

    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("me")
    @Expose
    private Boolean me;
    @SerializedName("permissionId")
    @Expose
    private String permissionId;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Boolean getMe() {
        return me;
    }

    public void setMe(Boolean me) {
        this.me = me;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("displayName", displayName).append("emailAddress", emailAddress).append("kind", kind).append("me", me).append("permissionId", permissionId).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(me).append(permissionId).append(emailAddress).append(displayName).append(kind).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LastModifyingUser) == false) {
            return false;
        }
        LastModifyingUser rhs = ((LastModifyingUser) other);
        return new EqualsBuilder().append(me, rhs.me).append(permissionId, rhs.permissionId).append(emailAddress, rhs.emailAddress).append(displayName, rhs.displayName).append(kind, rhs.kind).isEquals();
    }

}
