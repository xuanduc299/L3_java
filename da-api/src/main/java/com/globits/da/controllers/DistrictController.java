package com.globits.da.controllers;

import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.Response;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/district")
public class DistrictController {
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/all")
    public Response<List<DistrictDto>> getAll(){
        return districtService.getAll();
    }

    @PostMapping("/add")
    public Response<DistrictDto> add(@RequestBody DistrictDto dto){
        return districtService.create(dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        districtService.delete(id);
    }
    @PutMapping("/{id}")
    public Response<DistrictDto> edit(@PathVariable UUID id,@RequestBody DistrictDto dto){
        return districtService.edit(dto,id);
    }
    @GetMapping("/{id}")
    public Response<DistrictDto> getById(@PathVariable UUID id){
       return districtService.getById(id);
    }


}
