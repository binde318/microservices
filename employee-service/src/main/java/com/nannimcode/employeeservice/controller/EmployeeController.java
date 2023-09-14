package com.nannimcode.employeeservice.controller;

import com.nannimcode.employeeservice.model.Employee;
import com.nannimcode.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

  @RestController
  @RequestMapping("/employee")
   public class EmployeeController {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeRepository employeeRepository;
    @PostMapping("/add")
    public Employee add(@RequestBody Employee employee){
        LOGGER.info("Employee add : {}", employee);
       return employeeRepository.addEmployee(employee);
    }
    @GetMapping("/{id}")
    public Employee find(@PathVariable("id") Long id){
        LOGGER.info("Employee add : {}", id);
        return employeeRepository.findById(id);
    }
    @GetMapping("/all")
    public List<Employee> findAll(){
        LOGGER.info("find all employee");
        return employeeRepository.findAll();
    }
    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable("departmentId") Long departmentId){
        LOGGER.info("Employee find by department id : {}", departmentId);
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
