
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class StorageQuota implements Serializable {
    private final static long serialVersionUID = 150261210048956114L;

    @SerializedName("limit")
    @Expose
    private String limit;
    @SerializedName("usage")
    @Expose
    private String usage;
    @SerializedName("usageInDrive")
    @Expose
    private String usageInDrive;
    @SerializedName("usageInDriveTrash")
    @Expose
    private String usageInDriveTrash;

    public StorageQuota() {
    }

    public StorageQuota(String limit, String usage, String usageInDrive, String usageInDriveTrash) {
        super();
        this.limit = limit;
        this.usage = usage;
        this.usageInDrive = usageInDrive;
        this.usageInDriveTrash = usageInDriveTrash;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getUsageInDrive() {
        return usageInDrive;
    }

    public void setUsageInDrive(String usageInDrive) {
        this.usageInDrive = usageInDrive;
    }

    public String getUsageInDriveTrash() {
        return usageInDriveTrash;
    }

    public void setUsageInDriveTrash(String usageInDriveTrash) {
        this.usageInDriveTrash = usageInDriveTrash;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("limit", limit).append("usage", usage).append("usageInDrive", usageInDrive).append("usageInDriveTrash", usageInDriveTrash).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(limit).append(usageInDrive).append(usage).append(usageInDriveTrash).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StorageQuota) == false) {
            return false;
        }
        StorageQuota rhs = ((StorageQuota) other);
        return new EqualsBuilder().append(limit, rhs.limit).append(usageInDrive, rhs.usageInDrive).append(usage, rhs.usage).append(usageInDriveTrash, rhs.usageInDriveTrash).isEquals();
    }

}
