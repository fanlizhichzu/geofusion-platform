package io.github.fanlizhichzu.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.fanlizhichzu.manager.domain.entity.MapLayerDirectory;
import io.github.fanlizhichzu.manager.domain.vo.MapLayerDirectoryVO;
import io.github.fanlizhichzu.manager.service.MapLayerDirectoryService;
import io.github.fanlizhichzu.manager.mapper.MapLayerDirectoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lizhifan
 * @description 针对表【map_layer_directory】的数据库操作Service实现
 * @createDate 2025-07-21 23:01:14
 */
@Service
public class MapLayerDirectoryServiceImpl extends ServiceImpl<MapLayerDirectoryMapper, MapLayerDirectory>
        implements MapLayerDirectoryService {

    private final MapLayerDirectoryMapper mapLayerDirectoryMapper;

    public MapLayerDirectoryServiceImpl(MapLayerDirectoryMapper mapLayerDirectoryMapper) {
        this.mapLayerDirectoryMapper = mapLayerDirectoryMapper;
    }

    @Override
    public MapLayerDirectory add(MapLayerDirectory mapLayerDirectory) {
        mapLayerDirectoryMapper.insert(mapLayerDirectory);
        return mapLayerDirectory;
    }

    @Override
    public List<MapLayerDirectoryVO> getList() {
        List<MapLayerDirectory> mapLayerDirectoryList = mapLayerDirectoryMapper.selectList(null);
        return mapLayerDirectoryList.stream().map(mapLayerDirectory ->
                MapLayerDirectoryVO.builder()
                        .id(mapLayerDirectory.getId())
                        .directoryParent(mapLayerDirectory.getDirectoryParent())
                        .directoryName(mapLayerDirectory.getDirectoryName())
                        .directoryOrder(mapLayerDirectory.getDirectoryOrder())
                        .directoryTitle(mapLayerDirectory.getDirectoryTitle())
                        .build()
        ).toList();
    }

    @Override
    public MapLayerDirectory modify(MapLayerDirectory mapLayerDirectory) {
        mapLayerDirectoryMapper.updateById(mapLayerDirectory);
        return mapLayerDirectory;
    }
}




