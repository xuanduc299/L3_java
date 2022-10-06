package com.globits.da.controllers;

import com.globits.da.dto.Response;
import com.globits.da.dto.VillageDto;
import com.globits.da.service.VillageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/village")
public class VillageController {
    private final VillageService villageService;

    public VillageController(VillageService villageService) {
        this.villageService = villageService;
    }

    @GetMapping("/all")
    public Response<List<VillageDto>> getAllVillage() {
        return villageService.getAll();
    }

    @PostMapping("/add")
    public Response<VillageDto> add(@RequestBody VillageDto dto) {
        return villageService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        villageService.delete(id);
    }

    @PutMapping("/{id}")
    public Response<VillageDto> edit(@PathVariable UUID id, @RequestBody VillageDto dto) {
        return villageService.edit(dto, id);
    }

    @GetMapping("/{id}")
    public Response<VillageDto> getById(@PathVariable UUID id) {
        return villageService.getById(id);
    }
}
