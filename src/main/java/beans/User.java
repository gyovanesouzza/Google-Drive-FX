
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class User implements Serializable {
    private final static long serialVersionUID = -5719834289222902944L;

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
    @SerializedName("photoLink")
    @Expose
    private String photoLink;


    public User() {
    }

    public User(String displayName, String emailAddress, String kind, Boolean me, String permissionId, String photoLink) {
        super();
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.kind = kind;
        this.me = me;
        this.permissionId = permissionId;
        this.photoLink = photoLink;
    }

    public User(String displayName, String emailAddress, String permissionId, String photoLink) {
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.permissionId = permissionId;
        this.photoLink = photoLink;
    }

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

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("displayName", displayName).append("emailAddress", emailAddress).append("kind", kind).append("me", me).append("permissionId", permissionId).append("photoLink", photoLink).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(permissionId).append(emailAddress).append(displayName).append(kind).append(me).append(photoLink).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder().append(permissionId, rhs.permissionId).append(emailAddress, rhs.emailAddress).append(displayName, rhs.displayName).append(kind, rhs.kind).append(me, rhs.me).append(photoLink, rhs.photoLink).isEquals();
    }

}
