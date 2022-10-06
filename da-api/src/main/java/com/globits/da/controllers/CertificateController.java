package com.globits.da.controllers;

import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.Response;
import com.globits.da.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/all")
    public Response<List<CertificateDto>> getAllCertificate() {
        return certificateService.getAll();
    }

    @PostMapping("/add")
    public Response<CertificateDto> add(@RequestBody CertificateDto dto) {
        return certificateService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        certificateService.delete(id);
    }

    @PutMapping("/{id}")
    public Response<CertificateDto> edit(@PathVariable UUID id,@RequestBody CertificateDto dto){
        return certificateService.edit(dto,id);
    }

    @GetMapping("/{id}")
    public Response<CertificateDto> getById(@PathVariable UUID id){
        return certificateService.getById(id);
    }

}
