package com.globits.da.service.impl;

import com.globits.da.convert.Convert;
import com.globits.da.domain.District;
import com.globits.da.domain.Village;
import com.globits.da.dto.Response;
import com.globits.da.dto.VillageDto;
import com.globits.da.repository.VillageRepository;
import com.globits.da.service.VillageService;
import com.globits.da.validate.AddressValidate;
import com.globits.da.validate.ResponseStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VillageServiceImpl implements VillageService {
    private final VillageRepository villageRepository;
    private final Convert convert;

    private final AddressValidate addressValidate;

    public VillageServiceImpl(VillageRepository villageRepository, Convert convert, AddressValidate addressValidate) {
        this.villageRepository = villageRepository;
        this.convert = convert;
        this.addressValidate = addressValidate;
    }

    @Override
    public Response<List<VillageDto>> getAll() {
        List<Village> village = villageRepository.findAll();
        List<VillageDto> villageDto = new ArrayList<>();
        villageRepository.findAll().forEach(villages -> villageDto.add(new VillageDto(villages)));
        return new Response<>(villageDto);
    }

    @Override
    public Response<VillageDto> create(VillageDto dto) {
        ResponseStatus status = addressValidate.validateVillage(null, dto, true);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }

        District district = new District();
        district.setId(dto.getDistrictDto().getId());

        Village village = new Village();
        convert.convertDataIntoDTO(dto, village, district);
        village = villageRepository.save(village);
        return new Response<>(new VillageDto(village),ResponseStatus.SUCCESS);
    }

    @Override
    public Response<Boolean> delete(UUID id) {
        if (!villageRepository.existsById(id)) {
            return new Response<>(false, ResponseStatus.VILLAGE_NOT_EXIST);
        }

        villageRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS);
    }

    @Override
    public Response<VillageDto> edit(VillageDto dto, UUID id) {
        ResponseStatus status = addressValidate.validateVillage(null, dto, true);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }
        District district = new District();
        district.setId(dto.getDistrictDto().getId());

        Village village = villageRepository.getVillageById(id);
        convert.convertDataIntoDTO(dto, village, district);
        village = villageRepository.save(village);
        return new Response<>(new VillageDto(village),ResponseStatus.SUCCESS);
    }

    @Override
    public Response<VillageDto> getById(UUID id) {
        if (!villageRepository.existsById(id)) {
            return new Response<>(ResponseStatus.VILLAGE_NOT_EXIST);
        }

        Village village = villageRepository.getVillageById(id);
        return new Response<>(new VillageDto(village),ResponseStatus.SUCCESS);
    }
}
