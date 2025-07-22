package io.github.fanlizhichzu.manager.controller;

import io.github.fanlizhichzu.common.entity.vo.RestResponse;
import io.github.fanlizhichzu.manager.domain.entity.MapLayerDirectory;
import io.github.fanlizhichzu.manager.domain.vo.MapLayerDirectoryVO;
import io.github.fanlizhichzu.manager.service.MapLayerDirectoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mapLayerDirectory")
public class MapLayerDirectoryController {

    private final MapLayerDirectoryService mapLayerDirectoryService;

    public MapLayerDirectoryController(MapLayerDirectoryService mapLayerDirectoryService) {
        this.mapLayerDirectoryService = mapLayerDirectoryService;
    }

    @PostMapping("/add")
    public RestResponse<MapLayerDirectory> add(@RequestBody MapLayerDirectory mapLayerDirectory) {
        return RestResponse.success(mapLayerDirectoryService.add(mapLayerDirectory));
    }

    @GetMapping("/list")
    public RestResponse<List<MapLayerDirectoryVO>> list() {
        return RestResponse.success(mapLayerDirectoryService.getList());
    }
}
