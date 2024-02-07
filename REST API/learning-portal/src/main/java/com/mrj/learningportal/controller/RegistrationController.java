package com.mrj.learningportal.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrj.learningportal.dto.RegistrationResponseDto;
import com.mrj.learningportal.entity.RegistrationEntity;
import com.mrj.learningportal.service.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/registrations")
public class RegistrationController {
	
	private RegistrationService registrationService;
	
	@GetMapping
	public ResponseEntity<Object> showAllRegistrations()
	{
		List<RegistrationEntity> registrations = registrationService.findAllRegistrations();
		if(!registrations.isEmpty())
		{
			List<RegistrationResponseDto> registrationsresp = registrations.stream().map(registrationService::mapRegistrationEntitytoDto).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.FOUND).body(registrationsresp);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to fetch Registrations");
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> showRegistrationById(@PathVariable(value = "id") Long id)
	{
		Optional<RegistrationEntity> registrationEntity = registrationService.findRegistrationById(id);
		if(registrationEntity.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registration not found!");
		}
		RegistrationEntity reg = registrationEntity.get();
		RegistrationResponseDto regresp = registrationService.mapRegistrationEntitytoDto(reg);
		return ResponseEntity.status(HttpStatus.FOUND).body(regresp);
	}
	
}
