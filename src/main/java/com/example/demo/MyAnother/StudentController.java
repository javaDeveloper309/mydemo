package com.example.demo.MyAnother;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/student")
    public Student insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }
    @PostMapping("/enrollment")
    public Enrollment insertEnrollment(@RequestParam Long studentId,
                                       @RequestParam Long courseId,
                                       @RequestParam String grade) {
        return studentService.insertEnrollment(studentId, courseId, grade);
    }

    // Insert a new course
    @PostMapping("/course")
    public Course insertCourse(@RequestBody Course course) {
        return studentService.insertCourse(course);
    }

    // Fetch enrollment data
    @GetMapping("/enrollment")
    public Enrollment getEnrollment(@RequestParam Long studentId, @RequestParam Long courseId) {
        return studentService.fetchEnrollment(studentId, courseId);
    }

    // Update grade
    @PutMapping("/enrollment")
    public String updateEnrollmentGrade(@RequestParam Long studentId,
                                        @RequestParam Long courseId,
                                        @RequestParam String grade) {
        return studentService.updateEnrollmentGrade(studentId, courseId, grade);
    }
}
