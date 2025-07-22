package io.github.fanlizhichzu.manager.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName map_layer_directory
 */
@TableName(value ="map_layer_directory")
@Data
public class MapLayerDirectory implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 
     */
    @TableField(value = "directory_name")
    private String directoryName;

    /**
     * 
     */
    @TableField(value = "directory_title")
    private String directoryTitle;

    /**
     * 
     */
    @TableField(value = "directory_order")
    private Integer directoryOrder;

    /**
     * 
     */
    @TableField(value = "directory_parent")
    private String directoryParent;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MapLayerDirectory other = (MapLayerDirectory) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDirectoryName() == null ? other.getDirectoryName() == null : this.getDirectoryName().equals(other.getDirectoryName()))
            && (this.getDirectoryTitle() == null ? other.getDirectoryTitle() == null : this.getDirectoryTitle().equals(other.getDirectoryTitle()))
            && (this.getDirectoryOrder() == null ? other.getDirectoryOrder() == null : this.getDirectoryOrder().equals(other.getDirectoryOrder()))
            && (this.getDirectoryParent() == null ? other.getDirectoryParent() == null : this.getDirectoryParent().equals(other.getDirectoryParent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDirectoryName() == null) ? 0 : getDirectoryName().hashCode());
        result = prime * result + ((getDirectoryTitle() == null) ? 0 : getDirectoryTitle().hashCode());
        result = prime * result + ((getDirectoryOrder() == null) ? 0 : getDirectoryOrder().hashCode());
        result = prime * result + ((getDirectoryParent() == null) ? 0 : getDirectoryParent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", directoryName=").append(directoryName);
        sb.append(", directoryTitle=").append(directoryTitle);
        sb.append(", directoryOrder=").append(directoryOrder);
        sb.append(", directoryParent=").append(directoryParent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}