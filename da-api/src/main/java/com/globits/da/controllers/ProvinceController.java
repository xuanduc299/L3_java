package com.globits.da.controllers;

import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.Response;
import com.globits.da.service.ProvinceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/province")
public class ProvinceController {
    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("/all")
    public Response<List<ProvinceDto>> getAllProvince() {
        return provinceService.getAll();
    }

    @PostMapping("/add")
    public Response<ProvinceDto> add(@RequestBody ProvinceDto dto) {
       return provinceService.create(dto);
    }

    @DeleteMapping("/delete")
    public void delete(@PathVariable UUID id) {
        provinceService.delete(id);
    }

    @PutMapping("/{id}")
    public Response<ProvinceDto> edit(@PathVariable UUID id, @RequestBody ProvinceDto dto) {
       return provinceService.edit(dto, id);
    }

    @GetMapping("/{id}")
    public Response<ProvinceDto> getById(@PathVariable UUID id){
        return provinceService.getById(id);
    }

    @GetMapping("/{id}/district")
    public Response<List<DistrictDto>> getDistrictDto(@PathVariable UUID id){
        return provinceService.getDistrictDto(id);
    }

}
