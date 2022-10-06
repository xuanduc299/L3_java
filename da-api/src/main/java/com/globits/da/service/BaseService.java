package com.globits.da.service;

import com.globits.da.dto.Response;

import java.util.List;
import java.util.UUID;

public interface BaseService<T> {
    Response<List<T>> getAll();

    Response<T> create(T dto);

    Response<Boolean> delete(UUID id);

    Response<T> edit(T dto, UUID id);

    Response<T> getById(UUID id);

}
