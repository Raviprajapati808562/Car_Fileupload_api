package com.boot.service;

import com.boot.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
//    private final RestClient restClient = RestClient.create();
    private final RestTemplate restTemplate =new RestTemplate();

    public List<Employee> saveEmp(Employee employee) {
        employeeList.add(employee);
        return employeeList;
    }

    public String getCarApiData() {
        return restTemplate.getForObject("http://localhost:8081/getCar",String.class);
//        return restClient.get().uri("http://localhost:8081/getCar").retrieve().body(String.class);
    }
}
