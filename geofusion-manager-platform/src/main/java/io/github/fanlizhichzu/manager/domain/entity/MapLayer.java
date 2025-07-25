package io.github.fanlizhichzu.manager.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.github.fanlizhichzu.manager.typeHandle.JsonTypeHandler;
import lombok.Data;

/**
 * 
 * @TableName map_layer
 */
@TableName(value ="map_layer")
@Data
public class MapLayer implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 
     */
    private String layerName;

    /**
     * 
     */
    private String layerTitle;

    /**
     * 
     */
    private Integer layerOrder;

    /**
     * 
     */
    private String layerParent;

    /**
     * 
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Object layerConfig;

    /**
     * 
     */
    private Integer layerZindex;

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
        MapLayer other = (MapLayer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLayerName() == null ? other.getLayerName() == null : this.getLayerName().equals(other.getLayerName()))
            && (this.getLayerTitle() == null ? other.getLayerTitle() == null : this.getLayerTitle().equals(other.getLayerTitle()))
            && (this.getLayerOrder() == null ? other.getLayerOrder() == null : this.getLayerOrder().equals(other.getLayerOrder()))
            && (this.getLayerParent() == null ? other.getLayerParent() == null : this.getLayerParent().equals(other.getLayerParent()))
            && (this.getLayerConfig() == null ? other.getLayerConfig() == null : this.getLayerConfig().equals(other.getLayerConfig()))
            && (this.getLayerZindex() == null ? other.getLayerZindex() == null : this.getLayerZindex().equals(other.getLayerZindex()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLayerName() == null) ? 0 : getLayerName().hashCode());
        result = prime * result + ((getLayerTitle() == null) ? 0 : getLayerTitle().hashCode());
        result = prime * result + ((getLayerOrder() == null) ? 0 : getLayerOrder().hashCode());
        result = prime * result + ((getLayerParent() == null) ? 0 : getLayerParent().hashCode());
        result = prime * result + ((getLayerConfig() == null) ? 0 : getLayerConfig().hashCode());
        result = prime * result + ((getLayerZindex() == null) ? 0 : getLayerZindex().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", layerName=").append(layerName);
        sb.append(", layerTitle=").append(layerTitle);
        sb.append(", layerOrder=").append(layerOrder);
        sb.append(", layerParent=").append(layerParent);
        sb.append(", layerConfig=").append(layerConfig);
        sb.append(", layerZindex=").append(layerZindex);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}