package com.example.demo.dao;



import com.example.demo.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    // Fetch all tasks for a given employee
    @Query("SELECT t FROM Task t WHERE t.employee.id = :employeeId")
    List<Task> findTasksByEmployeeId(Long employeeId);
}
