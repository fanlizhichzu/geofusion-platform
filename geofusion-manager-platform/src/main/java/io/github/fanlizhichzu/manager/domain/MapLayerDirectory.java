package io.github.fanlizhichzu.manager.domain;

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
    private String directory_name;

    /**
     * 
     */
    @TableField(value = "directory_title")
    private String directory_title;

    /**
     * 
     */
    @TableField(value = "directory_order")
    private Integer directory_order;

    /**
     * 
     */
    @TableField(value = "directory_parent")
    private String directory_parent;

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
            && (this.getDirectory_name() == null ? other.getDirectory_name() == null : this.getDirectory_name().equals(other.getDirectory_name()))
            && (this.getDirectory_title() == null ? other.getDirectory_title() == null : this.getDirectory_title().equals(other.getDirectory_title()))
            && (this.getDirectory_order() == null ? other.getDirectory_order() == null : this.getDirectory_order().equals(other.getDirectory_order()))
            && (this.getDirectory_parent() == null ? other.getDirectory_parent() == null : this.getDirectory_parent().equals(other.getDirectory_parent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDirectory_name() == null) ? 0 : getDirectory_name().hashCode());
        result = prime * result + ((getDirectory_title() == null) ? 0 : getDirectory_title().hashCode());
        result = prime * result + ((getDirectory_order() == null) ? 0 : getDirectory_order().hashCode());
        result = prime * result + ((getDirectory_parent() == null) ? 0 : getDirectory_parent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", directory_name=").append(directory_name);
        sb.append(", directory_title=").append(directory_title);
        sb.append(", directory_order=").append(directory_order);
        sb.append(", directory_parent=").append(directory_parent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}