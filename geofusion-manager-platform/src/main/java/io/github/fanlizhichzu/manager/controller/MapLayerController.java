package io.github.fanlizhichzu.manager.controller;

import io.github.fanlizhichzu.common.entity.vo.RestResponse;
import io.github.fanlizhichzu.manager.domain.entity.MapLayer;
import io.github.fanlizhichzu.manager.service.MapLayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mapLayer")
public class MapLayerController {

    private final MapLayerService mapLayerService;

    public MapLayerController(MapLayerService mapLayerService) {
        this.mapLayerService = mapLayerService;
    }

    @PostMapping("/add")
    public RestResponse<MapLayer> add(@RequestBody MapLayer mapLayer){
        return RestResponse.success(mapLayerService.add(mapLayer));
    }

    @GetMapping("/list")
    public RestResponse<List<MapLayer>> list(){
        return RestResponse.success(mapLayerService.list());
    }

    @PostMapping("/modify")
    public RestResponse<MapLayer> modify(@RequestBody MapLayer mapLayer){
        return RestResponse.success(mapLayerService.modify(mapLayer));
    }

    @DeleteMapping("/delete")
    public RestResponse<Boolean> delete(@RequestParam("id") String id){
        return RestResponse.success(mapLayerService.removeById(id));
    }
}
