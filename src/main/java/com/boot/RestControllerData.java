package com.boot;

import com.boot.model.Employee;
import com.boot.service.EmployeeService;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/getApiData")
public class RestControllerData {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public Map<String, Object> getDetails() {
        Map<String, Object> map = new HashMap<>();
        map.put("Animal", "Cat");
        map.put("Fruit", "Mango");
        map.put("Colour", "Pink");
        map.put("Country", "Bharat");
        return map;
    }

    @PatchMapping("/")
    public String getRequestData(@RequestBody Map<String, Object> data) {
        System.out.println(" Controller API  C  VS2  " + data);
        return "Data Received " + data;
    }

    @PostMapping("/empData")
    public ResponseEntity<?> saveEmpRequestData(@RequestBody @NonNull Employee employee) {
        List<Employee> empData = null;
        if (employee.getSalary() >= 10000) {
            empData = employeeService.saveEmp(employee);
            return ResponseEntity.status(HttpStatus.OK).body(empData);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Salary must be greater than or equal to 10000");
        }
    }

    @GetMapping("/carApiEndPoint")
    public ResponseEntity<String> getCarDataUsingApi() {
        ResponseEntity<String> response = null;
        String newCar = employeeService.getCarApiData();
        if (newCar != null) {
            return ResponseEntity.ok("Car Data: " + newCar);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("API Unable to fetch Data");
        }
    }
}