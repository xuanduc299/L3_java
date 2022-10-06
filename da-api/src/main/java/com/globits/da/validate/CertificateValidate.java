package com.globits.da.validate;

import com.globits.da.dto.CertificateDto;
import com.globits.da.repository.CertificateRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class CertificateValidate {
    private final CertificateRepository certificateRepository;

    public CertificateValidate(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public ResponseStatus validate(UUID certificateId, CertificateDto dto) {
        if (certificateId != null && !certificateRepository.existsById(certificateId)) {
            return ResponseStatus.CERTIFICATE_ID_NO_EXIST;
        }
        if (!StringUtils.hasText(dto.getCode())) {
            return ResponseStatus.CERTIFICATE_CODE_IS_NULL;
        }
        if (!StringUtils.hasText(dto.getName())) {
            return ResponseStatus.CERTIFICATE_CODE_IS_NULL;
        }
        if (dto.getEffectFrom() == null) {
            return ResponseStatus.EXPIRATION_IS_NULL;
        }
        if (dto.getEffectTo() == null) {
            return ResponseStatus.EXPIRATION_IS_NULL;
        }
        if (!dto.getEffectTo().isAfter(dto.getEffectFrom())) {
            return ResponseStatus.EXPIRATION_EFFECTIVE_ERROR;
        }
        LocalDate now = LocalDate.now();
        if (!dto.getEffectTo().isAfter(now)) {
            return ResponseStatus.EXPIRATION_NOW_ERROR;
        }
        return ResponseStatus.SUCCESS;
    }
}
