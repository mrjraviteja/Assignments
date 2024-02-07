package com.mrj.learningportal.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mrj.learningportal.dto.CourseResponseDto;
import com.mrj.learningportal.dto.RegistrationResponseDto;
import com.mrj.learningportal.entity.CourseEntity;
import com.mrj.learningportal.entity.RegistrationEntity;
import com.mrj.learningportal.entity.UserEntity;
import com.mrj.learningportal.repository.RegistrationRepository;
import com.mrj.learningportal.service.CourseService;
import com.mrj.learningportal.service.RegistrationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService{

	private CourseService courseService;
	private RegistrationRepository registrationRepository;
	
	@Override
	public List<RegistrationEntity> findAllRegistrations() {
		return registrationRepository.findAll();
	}

	@Override
	public Optional<RegistrationEntity> findRegistrationById(Long id) {
		return registrationRepository.findById(id);
	}

	@Override
	public void saveRegistration(RegistrationEntity registrationEntity) {
		registrationRepository.save(registrationEntity);
	}

	@Override
	public boolean checkRegistrationByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity) {
		RegistrationEntity registrationEntity = registrationRepository.findByUserEntityAndCourseEntity(userEntity,courseEntity);
		if(registrationEntity == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public List<RegistrationEntity> findRegistrationByUserEntity(UserEntity userEntity) {
		return registrationRepository.findByUserEntity(userEntity);
	}

	@Override
	public boolean checkRegistrationByUser(UserEntity userEntity) {
		List<RegistrationEntity> registrations = registrationRepository.findByUserEntity(userEntity);
		if(registrations.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public List<CourseResponseDto> findEnrolledCoursesByUser(UserEntity userEntity) {
		List<RegistrationEntity> registrations = registrationRepository.findByUserEntity(userEntity);
		List<CourseEntity> courses = registrations.stream().map(registration -> registration.getCourseEntity()).collect(Collectors.toList());
		return courses.stream().map(courseService::mapCourseEntitytoCourseDto).collect(Collectors.toList());
	}

	@Override
	public RegistrationResponseDto mapRegistrationEntitytoDto(RegistrationEntity registrationEntity) {
		RegistrationResponseDto registrationDto = new RegistrationResponseDto();
		registrationDto.setId(registrationEntity.getId());
		registrationDto.setUserName(registrationEntity.getUserEntity().getName());
		registrationDto.setCourseName(registrationEntity.getCourseEntity().getName());
		return registrationDto;
	}
	
}
