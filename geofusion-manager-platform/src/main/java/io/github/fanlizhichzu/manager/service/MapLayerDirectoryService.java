package io.github.fanlizhichzu.manager.service;

import io.github.fanlizhichzu.manager.domain.entity.MapLayerDirectory;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.fanlizhichzu.manager.domain.vo.MapLayerDirectoryVO;

import java.util.List;

/**
* @author lizhifan
* @description 针对表【map_layer_directory】的数据库操作Service
* @createDate 2025-07-21 23:01:14
*/
public interface MapLayerDirectoryService extends IService<MapLayerDirectory> {

    MapLayerDirectory add(MapLayerDirectory mapLayerDirectory);

    List<MapLayerDirectoryVO> getList();

    MapLayerDirectory modify(MapLayerDirectory mapLayerDirectory);
}
