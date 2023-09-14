package com.nannimcode.departmentservice.controller;

import com.nannimcode.departmentservice.client.EmployeeClient;
import com.nannimcode.departmentservice.model.Department;
import com.nannimcode.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private static final Logger LOGGER =
             LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping("/add-department")
    public Department addDepartment(@RequestBody Department department){
        LOGGER.info("Department add : {}", department);
        return departmentRepository.addDepartment(department);
    }
    @GetMapping("/{id}")
    public Department findById(@PathVariable ("id")Long id){
        LOGGER.info("Department find by Id {}",id);
    return departmentRepository.findById(id);
    }
    @GetMapping("/find-all")
    public List<Department> findAll(){
        LOGGER.info("Department find all");
        return departmentRepository.findAll();
    }
    @GetMapping("/{departmentId}")
    public List<Department> findByDepartmentId(@PathVariable("departmentId") Long departmentId){
        return departmentRepository.findByDepartmentId(departmentId);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees(){
        LOGGER.info("Department find all");
        List<Department> departments
                = departmentRepository.findAll();
        departments.forEach(department -> department
                .setEmployees(employeeClient.findByDepartmentId(department.getId())));
        return departments;
    }

}
