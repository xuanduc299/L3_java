package com.globits.da.service.impl;

import com.globits.da.convert.Convert;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.Response;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import com.globits.da.validate.AddressValidate;
import com.globits.da.validate.ResponseStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final Convert convert;

    private final AddressValidate addressValidate;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, Convert convert, AddressValidate addressValidate) {
        this.provinceRepository = provinceRepository;
        this.convert = convert;
        this.addressValidate = addressValidate;
    }

    @Override
    public Response<List<ProvinceDto>> getAll() {
        List<Province> province = provinceRepository.findAll();
        List<ProvinceDto> provinceDto = new ArrayList<>();
        provinceRepository.findAll().forEach(provinces -> provinceDto.add(new ProvinceDto(provinces)));
        return new Response<>(provinceDto);
    }

    @Override
    public Response<ProvinceDto> create(ProvinceDto dto) {
        ResponseStatus status = addressValidate.validateProvince(null, dto);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }

        Province province = new Province();
        convert.convertDataIntoDTO(dto, province);
        province = provinceRepository.save(province);
        return new Response<>(new ProvinceDto(province),ResponseStatus.SUCCESS);
    }

    @Override
    public Response<Boolean> delete(UUID id) {
        if (provinceRepository.existsById(id)) {
            return new Response<>(false, ResponseStatus.PROVINCE_NOT_EXIST);
        }

        provinceRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS);
    }

    @Override
    public Response<ProvinceDto> edit(ProvinceDto dto, UUID id) {
        ResponseStatus status = addressValidate.validateProvince(null, dto);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }
        Province province = provinceRepository.getProvinceById(id);
        convert.convertDataIntoDTO(dto, province);
        province = provinceRepository.save(province);
        return new Response<>(new ProvinceDto(province),ResponseStatus.SUCCESS);
    }

    @Override
    public Response<ProvinceDto> getById(UUID id) {
        if (provinceRepository.existsById(id)) {
            return new Response<>(ResponseStatus.PROVINCE_NOT_EXIST);
        }

        Province province = provinceRepository.getProvinceById(id);
        return new Response<>(new ProvinceDto(province),ResponseStatus.SUCCESS);
    }

    @Override
    public Response<List<DistrictDto>> getDistrictDto(UUID id) {
        if(!provinceRepository.existsById(id)){
            return new Response<>(ResponseStatus.PROVINCE_NOT_EXIST);
        }
        List<DistrictDto> dtoList = new ArrayList<>();
        Province province = provinceRepository.getProvinceById(id);
        for (District district : province.getDistrictList()) {
            dtoList.add(new DistrictDto(district));
        }
        return new Response<>(dtoList,ResponseStatus.SUCCESS);
    }
}
