package com.mrj.learningportal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrj.learningportal.entity.RegistrationEntity;
import com.mrj.learningportal.service.CategoryService;
import com.mrj.learningportal.service.CourseService;
import com.mrj.learningportal.service.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/registrations")
public class RegistrationController {
	
	private RegistrationService registrationService;
	
	@GetMapping
	public List<RegistrationEntity> showAllRegistrations()
	{
		return registrationService.findAllRegistrations();
	}
	
	@GetMapping("/{id}")
	public Optional<RegistrationEntity> showRegistrationById(@PathVariable(value = "id") Long id)
	{
		return registrationService.findRegistrationById(id);
	}
	
}
