package io.github.fanlizhichzu.manager.service;

import io.github.fanlizhichzu.manager.domain.entity.MapLayer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author fanlz
* @description 针对表【map_layer】的数据库操作Service
* @createDate 2025-07-25 09:26:13
*/
public interface MapLayerService extends IService<MapLayer> {

    MapLayer add(MapLayer mapLayer);

    MapLayer modify(MapLayer mapLayer);
}
