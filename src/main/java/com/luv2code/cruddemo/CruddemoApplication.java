package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudents(studentDAO);
			queryForStudentsByLastName(studentDAO);
		};
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("White");

		for (Student student : students) {
			System.out.println(student.toString());
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();

		for (Student student : students) {
			System.out.println(student.toString());
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		Student newStudent = new Student("Andrew", "Boyle", "andrew@boyle.com");
		studentDAO.save(newStudent);

		Student savedStudent = studentDAO.findById(newStudent.getId());
		System.out.println(savedStudent.toString());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		Student tempStudent1 = new Student("Rory", "Allen", "rory@email.com");
		Student tempStudent2 = new Student("Alice", "White", "alice@email.com");
		Student tempStudent3 = new Student("Bob", "Blue", "bob@email.com");

		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating a new student object...");
		Student tempStudent = new Student("Paul", "Roe", "paul@email.com");

		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
