package com.globits.da.service.impl;

import com.globits.da.convert.Convert;
import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.Response;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.service.CertificateService;
import com.globits.da.validate.CertificateValidate;
import com.globits.da.validate.ResponseStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final Convert convert;

    private final CertificateValidate certificateValidate;

    public CertificateServiceImpl(CertificateRepository certificateRepository, Convert convert, CertificateValidate certificateValidate) {
        this.certificateRepository = certificateRepository;
        this.convert = convert;
        this.certificateValidate = certificateValidate;
    }

    @Override
    public Response<List<CertificateDto>> getAll() {
        List<Certificate> certificate = certificateRepository.findAll();
        List<CertificateDto> certificateDto = new ArrayList<>();
        certificateRepository.findAll().forEach(certificates -> certificateDto
                .add(new CertificateDto(certificates)));
        return new Response<>(certificateDto);
    }

    @Override
    public Response<CertificateDto> create(CertificateDto dto) {
        ResponseStatus status = certificateValidate.validate(null, dto);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }

        Certificate certificate = new Certificate();
        convert.convertDataIntoDTO(dto, certificate);
        certificate = certificateRepository.save(certificate);
        return new Response<>(new CertificateDto(certificate));
    }

    @Override
    public Response<Boolean> delete(UUID id) {
        if(!certificateRepository.existsById(id)){
            return new Response<>(false,ResponseStatus.SUCCESS);
        }
        certificateRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS);
    }

    @Override
    public Response<CertificateDto> edit(CertificateDto dto, UUID id) {
        ResponseStatus status = certificateValidate.validate(null, dto);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }

        Certificate certificate = certificateRepository.getCertificateById(id);
        convert.convertDataIntoDTO(dto, certificate);
        certificate = certificateRepository.save(certificate);
        return new Response<>(new CertificateDto(certificate));
    }

    @Override
    public Response<CertificateDto> getById(UUID id) {
        if(!certificateRepository.existsById(id)){
            return new Response<>(ResponseStatus.SUCCESS);
        }
        Certificate certificate = certificateRepository.getCertificateById(id);
        return new Response<>(new CertificateDto(certificate));
    }
}
