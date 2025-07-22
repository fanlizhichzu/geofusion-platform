package io.github.fanlizhichzu.manager.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MapLayerDirectoryVO {
    /**
     *
     */
    private String id;

    /**
     *
     */
    private String directoryName;

    /**
     *
     */
    private String directoryTitle;

    /**
     *
     */
    private Integer directoryOrder;

    /**
     *
     */
    private String directoryParent;
}
