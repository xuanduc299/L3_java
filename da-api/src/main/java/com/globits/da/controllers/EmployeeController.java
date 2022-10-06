package com.globits.da.controllers;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.Response;
import com.globits.da.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public Response<EmployeeDto> add(@RequestBody EmployeeDto dto) {
        return employeeService.create(dto);
    }

    @PutMapping("/{id}")
    public Response<EmployeeDto> edit(@PathVariable UUID id, @RequestBody EmployeeDto dto) {
        return employeeService.edit(dto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        employeeService.delete(id);
    }

    @GetMapping("/{id}")
    public Response<EmployeeDto> findById(@PathVariable UUID id) {
        return employeeService.getById(id);
    }

    @GetMapping("/all")
    public Response<List<EmployeeDto>> getAll() {
        return employeeService.getAll();
    }

}
