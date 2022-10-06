package com.globits.da.service.impl;

import com.globits.da.domain.District;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Province;
import com.globits.da.domain.Village;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.Response;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import com.globits.da.validate.EmployeeValidate;
import com.globits.da.validate.ResponseStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeValidate employeeValidate;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeValidate employeeValidate) {
        this.employeeRepository = employeeRepository;
        this.employeeValidate = employeeValidate;
    }

    @Override
    public Response<List<EmployeeDto>> getAll() {
        return null;
    }

    @Override
    public Response<EmployeeDto> create(EmployeeDto dto) {
        ResponseStatus status = employeeValidate.validate(null,dto);
        if(status != ResponseStatus.SUCCESS){
            return new Response<>(status);
        }
        Employee employee = new Employee();
        convertDtoIntoEntity(dto,employee);
        employee =employeeRepository.save(employee);
        return new Response<>(new EmployeeDto(employee),ResponseStatus.SUCCESS);
    }

    @Override
    public Response<Boolean> delete(UUID id) {
        employeeRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS);
    }

    @Override
    public Response<EmployeeDto> edit(EmployeeDto dto, UUID id) {
        ResponseStatus status = employeeValidate.validate(id,dto);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }
        Employee employee = employeeRepository.getEmployeeById(id);
        convertDtoIntoEntity(dto,employee);
        employee = employeeRepository.save(employee);

        return new Response<>(new EmployeeDto(employee),ResponseStatus.SUCCESS);
    }

    @Override
    public Response<EmployeeDto> getById(UUID id) {
        if(!employeeRepository.existsById(id)){
            return new Response<>(ResponseStatus.EMPLOYEE_ID_NO_EXIST);
        }
        Employee employee = employeeRepository.getEmployeeById(id);
        return new Response<>(new EmployeeDto(employee),ResponseStatus.SUCCESS);
    }



    private void convertDtoIntoEntity(EmployeeDto dto,Employee entity){
        entity.setName(dto.getName());
        entity.setCode(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setAge(dto.getAge());
        Province province = new Province();
        province.setId(dto.getProvinceId());
        entity.setProvince(province);
        District district = new District();
        district.setId(dto.getDistrictId());
        entity.setDistrict(district);
        Village village = new Village();
        village.setId(dto.getVillageId());
        entity.setVillage(village);
    }
}
