
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class DriveBean {

    @SerializedName("files")
    @Expose
    private List<File> files;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("files", files).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(files).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DriveBean) == false) {
            return false;
        }
        DriveBean rhs = ((DriveBean) other);
        return new EqualsBuilder().append(files, rhs.files).isEquals();
    }

}
