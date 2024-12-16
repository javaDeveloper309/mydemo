package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Task;
import com.example.demo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CrudController {

    @Autowired
    private CrudService crudService;
//
//    @Autowired
//    private CrudService crudService;
// CREATE Task
@PostMapping("/task")
public ResponseEntity<Task> createTask(@RequestBody Task task) {
    return ResponseEntity.ok(crudService.createTask(task));
}

    // READ Tasks by Employee ID
    @GetMapping("/tasks/employee/{employeeId}")
    public ResponseEntity<List<Task>> getTasksByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(crudService.getTasksByEmployeeId(employeeId));
    }

    // UPDATE Task
    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(crudService.updateTask(id, task));
    }

    // DELETE Task
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        crudService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }



/////////////////////////////////////////////////////

    // CREATE
    @PostMapping("/company")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.ok(crudService.createCompany(company));
    }

    @PostMapping("/department")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(crudService.createDepartment(department));
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(crudService.createEmployee(employee));
    }

    // READ
    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(crudService.getCompanyById(id));
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(crudService.getDepartmentById(id));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(crudService.getEmployeeById(id));
    }

    // UPDATE
    @PutMapping("/update")
    public ResponseEntity<String> updateEntities(@RequestBody UpdateRequest request) {
        crudService.updateEntities(request);
        return ResponseEntity.ok("Entities updated successfully");
    }

    // DELETE
    @DeleteMapping("/company/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        crudService.deleteCompany(id);
        return ResponseEntity.ok("Company deleted successfully");
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        crudService.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        crudService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
