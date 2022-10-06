package com.globits.da.service.impl;

import com.globits.da.convert.Convert;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.Response;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.DistrictService;
import com.globits.da.validate.AddressValidate;
import com.globits.da.validate.ResponseStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final Convert convert;

    private final AddressValidate addressValidate;

    public DistrictServiceImpl(DistrictRepository districtRepository, Convert convert, AddressValidate addressValidate) {
        this.districtRepository = districtRepository;
        this.convert = convert;
        this.addressValidate = addressValidate;
    }

    @Override
    public Response<List<DistrictDto>> getAll() {
        List<District> district = districtRepository.findAll();
        List<DistrictDto> districtDto = new ArrayList<>();
        districtRepository.findAll().forEach(districts -> districtDto.add(new DistrictDto(districts)));
        return new Response<>(districtDto);
    }

    @Override
    public Response<DistrictDto> create(DistrictDto dto) {
        ResponseStatus status = addressValidate.validateDistrict(null, dto, true);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }

        Province province = new Province();
        province.setId(dto.getProvinceDto().getId());

        District district = new District();
        convert.convertDataIntoDTO(dto, district, province);
        district = districtRepository.save(district);
        return new Response<>(new DistrictDto(district), ResponseStatus.SUCCESS);
    }

    @Override
    public Response<DistrictDto> edit(DistrictDto dto, UUID id) {
        ResponseStatus status = addressValidate.validateDistrict(null, dto, true);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }

        Province province = new Province();
        province.setId(dto.getProvinceDto().getId());

        District district = districtRepository.getDistrictById(id);
        convert.convertDataIntoDTO(dto, district, province);
        district = districtRepository.save(district);
        return new Response<>(new DistrictDto(district), ResponseStatus.SUCCESS);
    }

    @Override
    public Response<Boolean> delete(UUID id) {
        if (!districtRepository.existsById(id)) {
            return new Response<>(false, ResponseStatus.DISTRICT_NOT_EXIST);
        }

        districtRepository.deleteById(id);
        return new Response<>(true, ResponseStatus.SUCCESS);
    }


    @Override
    public Response<DistrictDto> getById(UUID id) {
        if (!districtRepository.existsById(id)) {
            return new Response<>(ResponseStatus.DISTRICT_NOT_EXIST);
        }

        District district = districtRepository.getDistrictById(id);
        return new Response<>(new DistrictDto(district), ResponseStatus.SUCCESS);
    }
}
