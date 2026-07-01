package com.cognizant.week2.jpa3.ex06;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class ManyToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManyToManyApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(StudentRepository studentRepository, CourseRepository courseRepository) {
        return args -> {
            Course java = courseRepository.save(new Course("Java"));
            Course spring = courseRepository.save(new Course("Spring"));
            Student anil = new Student("Anil");
            anil.enroll(java);
            anil.enroll(spring);
            studentRepository.save(anil);
            studentRepository.findAll().forEach(student ->
                System.out.println(student.getName() + " -> " + student.getCourses().size()));
        };
    }
}

@Entity
@Table(name = "student")
class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private Set<Course> courses = new HashSet<>();

    Student() {
    }

    Student(String name) {
        this.name = name;
    }

    void enroll(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    public String getName() {
        return name;
    }

    public Set<Course> getCourses() {
        return courses;
    }
}

@Entity
@Table(name = "course")
class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    Course() {
    }

    Course(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return students;
    }
}

interface StudentRepository extends JpaRepository<Student, Long> {
}

interface CourseRepository extends JpaRepository<Course, Long> {
}
