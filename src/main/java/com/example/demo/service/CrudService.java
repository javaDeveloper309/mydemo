package com.example.demo.service;
import com.example.demo.controller.UpdateRequest;
import com.example.demo.dao.CompanyRepository;
import com.example.demo.dao.DepartmentRepository;
import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dao.TaskRepository;
import com.example.demo.entity.Company;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CrudService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TaskRepository taskRepository;


    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;




    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // READ Task by Employee ID
    public List<Task> getTasksByEmployeeId(Long employeeId) {
        return taskRepository.findTasksByEmployeeId(employeeId);
    }

    // UPDATE Task
    public Task updateTask(Long taskId, Task updatedTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setName(updatedTask.getName());
        task.setStatus(updatedTask.getStatus());
        task.setEmployee(updatedTask.getEmployee());
        return taskRepository.save(task);
    }

    // DELETE Task
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
    /*=================================================*/


    // Create Company, Department, Employee
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Read Company, Department, Employee by ID
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Update Company, Department, Employee (This ensures that all tables are updated based on conditions)
    @Transactional
    public void updateEntities(UpdateRequest request) {
        // Update Company
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));
        if (request.getCompanyName() != null) {
            company.setName(request.getCompanyName()+"rohit");
            companyRepository.save(company);
        }

        // Update Department
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        if (request.getDepartmentName() == null) {
            department.setName("siddharth");
            departmentRepository.save(department);
        }

        // Update Employee
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        if (request.getEmployeeName() != null) {
            employee.setName(request.getEmployeeName()+request.getEmployeeName().length());
        }
        if (request.getRole() != null) {
            employee.setRole(request.getRole());
        }
        employeeRepository.save(employee);
    }

    // Delete Company, Department, Employee by ID
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
