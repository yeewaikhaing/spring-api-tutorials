package com.mmit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mmit.model.entity.Employee;
import com.mmit.model.service.EmployeeService;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@GetMapping("add")
	public String showAddForm(ModelMap model) {
		
		model.put("employee", new Employee());
		
		return "add";
	}
	
	@GetMapping("/edit/{id}")
	public String editEmployee(@PathVariable("id") int empId,ModelMap model) {
		Employee emp=service.findById(empId);
		model.put("employee", emp);
		return "add";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		service.delete(id);
		return "redirect:/";
	}
	@PostMapping("add")
	public String addEmployee(@Valid @ModelAttribute("employee")Employee emp,BindingResult bindResult) {
		
		if(bindResult.hasErrors())
			return "add";
		else {
			service.save(emp);
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/")
	public String index(ModelMap model) {
		List<Employee> emplist=service.findAll();
		model.put("employees", emplist);
		return "index";
	}
	@ModelAttribute("positions")
	public List<String> getPositions(){
		return List.of("Programmer","Developer","Designer","Accountant");
	}
}
