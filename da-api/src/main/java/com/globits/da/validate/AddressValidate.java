package com.globits.da.validate;

import com.globits.da.Constants;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.VillageDto;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.repository.VillageRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AddressValidate {
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final VillageRepository villageRepository;

    public AddressValidate(ProvinceRepository provinceRepository, DistrictRepository districtRepository, VillageRepository villageRepository) {
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.villageRepository = villageRepository;
    }

    public ResponseStatus validateProvince(UUID provinceId, ProvinceDto dto) {
        if (provinceId != null && provinceRepository.existsById(provinceId)) {
            return ResponseStatus.PROVINCE_NOT_EXIST;
        }
        if (!StringUtils.hasText(dto.getCode())) {
            return ResponseStatus.PROVINCE_CODE_IS_NULL;
        }
        if (!StringUtils.hasText(dto.getName())) {
            return ResponseStatus.PROVINCE_CODE_IS_NULL;
        }
        if (dto.getArea() == null || dto.getArea() <= Constants.AREA_MIN) {
            return ResponseStatus.PROVINCE_AREA_ERROR;
        }
//        if(provinceId != null && provinceRepository.hashCode(provinceId,dto.getCode())){
//
//        }
//        ResponseStatus status = validateDistrict()
        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validateDistrict(UUID districtId, DistrictDto dto, boolean checkProvince) {
        return validateDistrict(districtId,dto,checkProvince,new ArrayList<>());
    }


    public ResponseStatus validateDistrict(UUID districtId, DistrictDto dto, boolean checkProvince, List<String> newCode) {
        if (districtId != null && provinceRepository.existsById(districtId)) {
            return ResponseStatus.DISTRICT_CODE_IS_EXIST;
        }
        if (!StringUtils.hasText(dto.getCode())) {
            return ResponseStatus.DISTRICT_CODE_IS_NULL;
        }
        if (!StringUtils.hasText(dto.getName())) {
            return ResponseStatus.DISTRICT_CODE_IS_NULL;
        }
        if (dto.getArea() == null || dto.getArea() <= Constants.AREA_MIN) {
            return ResponseStatus.DISTRICT_AREA_ERROR;
        }
        UUID provinceId = dto.getProvinceDto() == null ? null : dto.getProvinceDto().getId();
        if (checkProvince && !provinceRepository.existsById(provinceId)) {
            return ResponseStatus.PROVINCE_NOT_EXIST;
        }
        if (checkProvince && provinceId == null) {
            return ResponseStatus.PROVINCE_ID_IS_NULL;
        }
        ResponseStatus status = validateListVillage(districtId, dto, newCode);
        if (status != ResponseStatus.SUCCESS) {
            return status;
        }
        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validateVillage(UUID villageId, VillageDto dto, boolean checkDistrict) {
        if (villageId != null && !villageRepository.existsById(villageId)) {
            return ResponseStatus.VILLAGE_NOT_EXIST;
        }
        if (!StringUtils.hasText(dto.getCode())) {
            return ResponseStatus.VILLAGE_CODE_IS_NULL;
        }
        if (!StringUtils.hasText(dto.getName())) {
            return ResponseStatus.VILLAGE_CODE_IS_NULL;
        }
        if (dto.getArea() == null || dto.getArea() <= Constants.AREA_MIN) {
            return ResponseStatus.VILLAGES_AREA_ERROR;
        }
        UUID districtId = dto.getDistrictDto() == null ? null : dto.getDistrictDto().getId();
        if (checkDistrict && districtId == null) {
            return ResponseStatus.DISTRICT_ID_IS_NULL;
        }
        if (checkDistrict && districtRepository.existsById(districtId)) {
            return ResponseStatus.DISTRICT_NOT_EXIST;
        }
        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validateListVillage(UUID districtId, DistrictDto dto, List<String> newCode) {
        List<VillageDto> villageDtoList = dto.getVillageDto();
        if (!CollectionUtils.isEmpty(villageDtoList)) {
            for (VillageDto element : villageDtoList) {
                UUID villageId = element.getId();
                ResponseStatus status = validateVillage(villageId, element, false);
                if (status != ResponseStatus.SUCCESS) {
                    return status;
                }
                if (villageId != null) {
                    return ResponseStatus.NON_DISTRIC_VILLAGE;
                }
                if (isCodeDuplicate(newCode, element.getCode())) {
                    return ResponseStatus.VILLAGES_CODE_DUPLICATE;
                }
            }
        }
        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validateListDistrict(UUID provinceId, ProvinceDto dto, List<String> newCode) {
        List<DistrictDto> districtDtoList = dto.getDistrictDto();
        if (!CollectionUtils.isEmpty(districtDtoList)) {
            List<String> ListNewDistrictCode = new ArrayList<>();
            List<String> LisNewVillageCode = new ArrayList<>();
            for (DistrictDto element : districtDtoList) {
                UUID districtId = element.getId();
                ResponseStatus status = validateDistrict(districtId, element, false, newCode);
                if (status != ResponseStatus.SUCCESS) {
                    return status;
                }
                if (districtId != null) {
                    UUID districtProvinceId = districtRepository.getDistrictById(districtId).getProvince().getId();
                    if (!districtProvinceId.equals(provinceId)) {
                        return ResponseStatus.NON_PROVINCAL_DISTRICT;
                    }
                }
                if (isCodeDuplicate(newCode, element.getCode())) {
                    return ResponseStatus.DISTRICT_CODE_DUPLICATE;
                }
            }
        }
        return ResponseStatus.SUCCESS;
    }

    private boolean isCodeDuplicate(List<String> list, String newCode) {
        for (String element : list) {
            if (element.equals(newCode)) {
                return true;
            }
        }
        list.add(newCode);
        return false;
    }


}
