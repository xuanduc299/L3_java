package com.globits.da.validate;

import com.globits.da.Constants;
import com.globits.da.domain.District;
import com.globits.da.domain.Village;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.repository.VillageRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmployeeValidate {
    private final EmployeeRepository employeeRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final VillageRepository villageRepository;

    public EmployeeValidate(EmployeeRepository employeeRepository, ProvinceRepository provinceRepository, DistrictRepository districtRepository, VillageRepository villageRepository) {
        this.employeeRepository = employeeRepository;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.villageRepository = villageRepository;
    }

    public ResponseStatus validate(UUID employeeId, EmployeeDto dto) {
        if (employeeId != null && !employeeRepository.existsById(employeeId)) {
            return ResponseStatus.EMPLOYEE_ID_NO_EXIST;
        }
        ResponseStatus status = validateCode(employeeId, dto.getCode());
        if (status != ResponseStatus.SUCCESS) {
            return status;
        }
        if (!StringUtils.hasText(dto.getName())) {
            return ResponseStatus.EMPLOYEE_NAME_IS_NULL;
        }

        status = validateEmail(dto.getEmail());
        if (status != ResponseStatus.SUCCESS) {
            return status;
        }
        status = validatePhone(dto.getPhone());
        if (status != ResponseStatus.SUCCESS) {
            return status;
        }
        status = validateAge(Integer.valueOf(dto.getAge()));
        if (status != ResponseStatus.SUCCESS) {
            return status;
        }
        UUID ProvinceId = dto.getProvinceId();
        UUID DistrictId = dto.getDistrictId();
        UUID VillageId = dto.getVillageId();
        status = validateAddress(ProvinceId, DistrictId, VillageId);
        if (status != ResponseStatus.SUCCESS) {
            return status;
        }
        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validateCode(UUID employeeId, String code) {
        if (!StringUtils.hasText(code)) {
            return ResponseStatus.EMPLOYEE_CODE_IS_NULL;
        }
        Pattern pattern = Pattern.compile(Constants.REGEX_CODE);
        Matcher matcher = pattern.matcher(code);
        if (!matcher.find()) {
            return ResponseStatus.EMPLOYEE_CODE_WRONG_FORMAT;
        }
        if (employeeId == null && employeeRepository.existsByCode(code)) {
            return ResponseStatus.EMPLOYEE_CODE_IS_EXIST;
        }
        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validateEmail(String email) {
        Pattern pattern = Pattern.compile(Constants.REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            return ResponseStatus.EMPLOYEE_CODE_WRONG_FORMAT;
        }
        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validatePhone(String phone) {
        Pattern pattern = Pattern.compile(Constants.REGEX_PHONE);
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.find()) {
            return ResponseStatus.EMPLOYEE_CODE_WRONG_FORMAT;
        }
        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validateAge(Integer age) {
        if (age == null) {
            return ResponseStatus.AGE_IS_NULL;
        }
        if (age <= Constants.MIN_AGE) {
            return ResponseStatus.AGE_WRONG_FORMAT;
        }
        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validateAddress(UUID provinceId, UUID districtId, UUID villageId) {
        if (provinceId == null) {
            return ResponseStatus.PROVINCE_ID_IS_NULL;
        }
        if (!provinceRepository.existsById(provinceId)) {
            return ResponseStatus.PROVINCE_NOT_EXIST;
        }
        if (districtId == null) {
            return ResponseStatus.DISTRICT_ID_IS_NULL;
        }
        if (!provinceRepository.existsById(provinceId)) {
            return ResponseStatus.DISTRICT_NOT_EXIST;
        }
        if (villageId == null) {
            return ResponseStatus.VILLAGE_ID_IS_NULL;
        }
        if (!provinceRepository.existsById(provinceId)) {
            return ResponseStatus.VILLAGE_NOT_EXIST;
        }
        District district = districtRepository.getDistrictById(districtId);
        if (!provinceId.equals(district.getProvince().getId())) {
            return ResponseStatus.NON_PROVINCAL_DISTRICT;
        }
        Village village = villageRepository.getVillageById(villageId);
        if (!districtId.equals(village.getDistrict().getId())) {
            return ResponseStatus.NON_DISTRIC_VILLAGE;
        }
        return ResponseStatus.SUCCESS;
    }


}
