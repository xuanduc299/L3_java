package com.globits.da.convert;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.domain.Village;
import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.VillageDto;
import com.globits.da.repository.DistrictRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class Convert {
    private final DistrictRepository districtRepository;

    public Convert(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

//    public List<DistrictDto> getAllDistrict() {
//        return ((List<District>) districtRepository
//                .findAll())
//                .stream()
//                .map(this::convertDataIntoDTO)
//                .collect(Collectors.toList());
//    }

//    public DistrictDto convertDataIntoDTO(District district) {
//        DistrictDto districtDto = new DistrictDto();
//        districtDto.setCode(district.getCode());
//        districtDto.setName(district.getName());
//        districtDto.setArea(district.getArea());
//        return districtDto;
//    }
    public void convertDataIntoDTO(CertificateDto certificateDto, Certificate certificate) {
        certificate.setCode(certificateDto.getCode());
        certificate.setName(certificateDto.getName());
        certificate.setEffectFrom(certificateDto.getEffectFrom());
        certificate.setEffectTo(certificateDto.getEffectTo());
    }

    public void convertDataIntoDTO(VillageDto villageDto, Village village, District district) {
        village.setCode(villageDto.getCode());
        village.setName(villageDto.getName());
        village.setArea(villageDto.getArea());
        village.setDistrict(district);
    }

    

    public void convertDataIntoDTO(DistrictDto districtDto, District district,Province province) {
        district.setCode(districtDto.getCode());
        district.setName(districtDto.getName());
        district.setArea(districtDto.getArea());
        district.setProvince(province);

        List<VillageDto> villageDto = districtDto.getVillageDto();

        if(!villageDto.isEmpty()){
            List<Village> villageList = new ArrayList<>();
            villageDto.forEach(new Consumer<VillageDto>() {
                @Override
                public void accept(VillageDto villageDto) {
                    Village village = new Village();
                    convertDataIntoDTO(villageDto,village,district);
                    villageList.add(village);
                }
            });
            district.setVillage(villageList);
        }
    }

    public void convertDataIntoDTO(ProvinceDto provinceDto, Province province) {
        province.setCode(provinceDto.getCode());
        province.setName(provinceDto.getName());
        province.setArea(provinceDto.getArea());
        List<DistrictDto> districtDto  = provinceDto.getDistrictDto();

        if (!districtDto.isEmpty()){
            List<District> districtList = new ArrayList<>();
            districtDto.forEach(new Consumer<DistrictDto>() {
                @Override
                public void accept(DistrictDto DistrictDto) {
                    District district = new District();
                    convertDataIntoDTO(DistrictDto, district, province);
                    districtList.add(district);
                }
            });
            province.setDistrictList(districtList);
        }
    }
}
