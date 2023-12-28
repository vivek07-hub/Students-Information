package com.becoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.becoder.entity.Student;
import com.becoder.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	//Handler Method to list students and return model and view
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
		
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		//create student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
		
	}
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id,Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
		
	}
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,@ModelAttribute("student") Student student, Model model) {
		
		//get Studnet from database by id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(student.getId());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		//save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}

}
