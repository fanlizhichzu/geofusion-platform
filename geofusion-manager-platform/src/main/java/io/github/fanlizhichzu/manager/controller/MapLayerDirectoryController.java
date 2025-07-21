package io.github.fanlizhichzu.manager.controller;

import io.github.fanlizhichzu.common.entity.vo.RestResponse;
import io.github.fanlizhichzu.manager.domain.MapLayerDirectory;
import io.github.fanlizhichzu.manager.service.MapLayerDirectoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
