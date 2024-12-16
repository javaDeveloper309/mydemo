package com.example.demo.MyAnother;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;


    // Insert a new student
    public Student insertStudent(Student student) {
        return studentRepository.save(student);
    }

    // Insert a new course
    public Course insertCourse(Course course) {
        return courseRepository.save(course);
    }

    // Insert a new enrollment (many-to-many relationship between student and course)
    public Enrollment insertEnrollment(Long studentId, Long courseId, String grade) {
        // Fetch the student and course by their IDs
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        // Create a new enrollment and set relationships
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade(grade);

        // Save the enrollment and return it
        return enrollmentRepository.save(enrollment);
    }

    // Insert a new student with enrollments (creating both student and course relationships)
    public Student insertStudentWithCourses(Student student, Set<Long> courseIds, String grade) {
        Set<Enrollment> enrollments = new HashSet<>();

        for (Long courseId : courseIds) {
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setGrade(grade);
            enrollments.add(enrollment);
        }

        student.setEnrollments(enrollments);

        // Save the student with their enrollments
        return studentRepository.save(student);
    }


    // Fetch data from two tables
    public Enrollment fetchEnrollment(Long studentId, Long courseId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findAll()
                .stream()
                .filter(e -> e.getStudent().getId().equals(studentId) && e.getCourse().getId().equals(courseId))
                .findFirst();
        return enrollment.orElse(null);
    }

    // Update using two tables
    public String updateEnrollmentGrade(Long studentId, Long courseId, String newGrade) {
        Enrollment enrollment = fetchEnrollment(studentId, courseId);
        if (enrollment != null) {
            enrollment.setGrade(newGrade);
            enrollmentRepository.save(enrollment);
            return "Grade updated successfully";
        } else {
            return "Enrollment not found";
        }
    }
}
