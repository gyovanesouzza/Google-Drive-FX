
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class File{

    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("fileExtension")
    @Expose
    private String fileExtension;
    @SerializedName("iconLink")
    @Expose
    private String iconLink;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("lastModifyingUser")
    @Expose
    private LastModifyingUser lastModifyingUser;
    @SerializedName("mimeType")
    @Expose
    private String mimeType;
    @SerializedName("modifiedByMe")
    @Expose
    private Boolean modifiedByMe;
    @SerializedName("modifiedByMeTime")
    @Expose
    private String modifiedByMeTime;
    @SerializedName("modifiedTime")
    @Expose
    private Date modifiedTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owners")
    @Expose
    private List<Owner> owners = null;
    @SerializedName("shared")
    @Expose
    private Boolean shared;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("starred")
    @Expose
    private Boolean starred;
    @SerializedName("thumbnailLink")
    @Expose
    private String thumbnailLink;
    @SerializedName("trashed")
    @Expose
    private Boolean trashed;
    @SerializedName("viewedByMe")
    @Expose
    private Boolean viewedByMe;
    @SerializedName("viewedByMeTime")
    @Expose
    private String viewedByMeTime;
    @SerializedName("webContentLink")
    @Expose
    private String webContentLink;
    @SerializedName("webViewLink")
    @Expose
    private String webViewLink;
    @SerializedName("sharedWithMeTime")
    @Expose
    private String sharedWithMeTime;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public LastModifyingUser getLastModifyingUser() {
        return lastModifyingUser;
    }

    public void setLastModifyingUser(LastModifyingUser lastModifyingUser) {
        this.lastModifyingUser = lastModifyingUser;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Boolean getModifiedByMe() {
        return modifiedByMe;
    }

    public void setModifiedByMe(Boolean modifiedByMe) {
        this.modifiedByMe = modifiedByMe;
    }

    public String getModifiedByMeTime() {
        return modifiedByMeTime;
    }

    public void setModifiedByMeTime(String modifiedByMeTime) {
        this.modifiedByMeTime = modifiedByMeTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public Boolean getTrashed() {
        return trashed;
    }

    public void setTrashed(Boolean trashed) {
        this.trashed = trashed;
    }

    public Boolean getViewedByMe() {
        return viewedByMe;
    }

    public void setViewedByMe(Boolean viewedByMe) {
        this.viewedByMe = viewedByMe;
    }

    public String getViewedByMeTime() {
        return viewedByMeTime;
    }

    public void setViewedByMeTime(String viewedByMeTime) {
        this.viewedByMeTime = viewedByMeTime;
    }

    public String getWebContentLink() {
        return webContentLink;
    }

    public void setWebContentLink(String webContentLink) {
        this.webContentLink = webContentLink;
    }

    public String getWebViewLink() {
        return webViewLink;
    }

    public void setWebViewLink(String webViewLink) {
        this.webViewLink = webViewLink;
    }

    public String getSharedWithMeTime() {
        return sharedWithMeTime;
    }

    public void setSharedWithMeTime(String sharedWithMeTime) {
        this.sharedWithMeTime = sharedWithMeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("createdTime", createdTime).append("fileExtension", fileExtension).append("iconLink", iconLink).append("id", id).append("kind", kind).append("lastModifyingUser", lastModifyingUser).append("mimeType", mimeType).append("modifiedByMe", modifiedByMe).append("modifiedByMeTime", modifiedByMeTime).append("modifiedTime", modifiedTime).append("name", name).append("owners", owners).append("shared", shared).append("size", size).append("starred", starred).append("thumbnailLink", thumbnailLink).append("trashed", trashed).append("viewedByMe", viewedByMe).append("viewedByMeTime", viewedByMeTime).append("webContentLink", webContentLink).append("webViewLink", webViewLink).append("sharedWithMeTime", sharedWithMeTime).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(webContentLink).append(modifiedByMeTime).append(modifiedTime).append(shared).append(kind).append(owners).append(webViewLink).append(mimeType).append(thumbnailLink).append(viewedByMeTime).append(lastModifyingUser).append(viewedByMe).append(iconLink).append(size).append(starred).append(fileExtension).append(modifiedByMe).append(name).append(createdTime).append(id).append(sharedWithMeTime).append(trashed).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof File) == false) {
            return false;
        }
        File rhs = ((File) other);
        return new EqualsBuilder().append(webContentLink, rhs.webContentLink).append(modifiedByMeTime, rhs.modifiedByMeTime).append(modifiedTime, rhs.modifiedTime).append(shared, rhs.shared).append(kind, rhs.kind).append(owners, rhs.owners).append(webViewLink, rhs.webViewLink).append(mimeType, rhs.mimeType).append(thumbnailLink, rhs.thumbnailLink).append(viewedByMeTime, rhs.viewedByMeTime).append(lastModifyingUser, rhs.lastModifyingUser).append(viewedByMe, rhs.viewedByMe).append(iconLink, rhs.iconLink).append(size, rhs.size).append(starred, rhs.starred).append(fileExtension, rhs.fileExtension).append(modifiedByMe, rhs.modifiedByMe).append(name, rhs.name).append(createdTime, rhs.createdTime).append(id, rhs.id).append(sharedWithMeTime, rhs.sharedWithMeTime).append(trashed, rhs.trashed).isEquals();
    }

}
