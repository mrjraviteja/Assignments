package com.mrj.learningportal.service;

import java.util.List;
import java.util.Optional;

import com.mrj.learningportal.entity.CourseEntity;
import com.mrj.learningportal.entity.RegistrationEntity;
import com.mrj.learningportal.entity.UserEntity;

public interface RegistrationService {
	public List<RegistrationEntity> findAllRegistrations();
	public Optional<RegistrationEntity> findRegistrationById(Long id);
	public void saveRegistration(RegistrationEntity registrationEntity);
	public boolean checkRegistrationByUserAndCourse(UserEntity userEntity,CourseEntity courseEntity);
	public boolean checkRegistrationByUser(UserEntity userEntity);
	public List<RegistrationEntity> findRegistrationByUserEntity(UserEntity userEntity);
	public List<CourseEntity> findEnrolledCoursesByUser(UserEntity userEntity);
}