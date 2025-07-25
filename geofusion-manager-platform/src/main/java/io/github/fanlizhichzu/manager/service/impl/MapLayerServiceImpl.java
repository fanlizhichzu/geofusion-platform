package io.github.fanlizhichzu.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.fanlizhichzu.manager.domain.entity.MapLayer;
import io.github.fanlizhichzu.manager.service.MapLayerService;
import io.github.fanlizhichzu.manager.mapper.MapLayerMapper;
import org.springframework.stereotype.Service;

/**
* @author fanlz
* @description 针对表【map_layer】的数据库操作Service实现
* @createDate 2025-07-25 09:26:13
*/
@Service
public class MapLayerServiceImpl extends ServiceImpl<MapLayerMapper, MapLayer>
    implements MapLayerService{

    @Override
    public MapLayer add(MapLayer mapLayer) {
        this.baseMapper.insert(mapLayer);
        return mapLayer;
    }

    @Override
    public MapLayer modify(MapLayer mapLayer) {
        this.baseMapper.updateById(mapLayer);
        return mapLayer;
    }
}




