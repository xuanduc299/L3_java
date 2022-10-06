package com.globits.da.service;


import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.Response;

import java.util.List;
import java.util.UUID;


public interface ProvinceService extends BaseService<ProvinceDto> {
    Response<List<DistrictDto>> getDistrictDto(UUID id);
}
