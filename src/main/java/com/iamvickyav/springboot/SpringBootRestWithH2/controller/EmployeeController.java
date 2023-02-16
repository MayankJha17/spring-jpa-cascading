package com.iamvickyav.springboot.SpringBootRestWithH2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iamvickyav.springboot.SpringBootRestWithH2.model.Employee;
import com.iamvickyav.springboot.SpringBootRestWithH2.service.EmployeeRepository;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;

    // Select, Insert, Delete, Update Operations for an Employee

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    Employee getEmployee(@PathVariable Integer id){
        return  employeeRepo.findById(id).get();
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    String addEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeRepo.save(employee);
        return "SUCCESS";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    Employee updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeRepo.save(employee);
        return updatedEmployee;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    Map<String, String> deleteEmployee(@RequestParam Integer id){
        Map<String, String> status = new HashMap<>();
        Optional<Employee> employee = employeeRepo.findById(id);
        if(employee.isPresent()) {
            employeeRepo.delete(employee.get());
            status.put("Status", "Employee deleted successfully");
        }
        else {
            status.put("Status", "Employee not exist");
        }
        return status;
    }

    // Select, Insert, Delete for List of Employees

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    List<Employee> getAllEmployee(){
        return employeeRepo.findAll();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    String addAllEmployees(@RequestBody List<Employee> employeeList){
        employeeRepo.saveAll(employeeList);
        return "SUCCESS";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.DELETE)
    String addAllEmployees(){
        employeeRepo.deleteAll();
        return "SUCCESS";
    }
}
