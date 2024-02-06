package com.mrj.learningportal.dto;

import java.util.List;

import com.mrj.learningportal.entity.CourseEntity;
import com.mrj.learningportal.entity.UserEntity;

import lombok.Data;

@Data
public class UserResponseDto {
	
	private Long id;
	private String name;
	private UserEntity.Roles role;
	private List<CourseEntity> enrolledCourses;
	private List<CourseEntity> favouriteCourses;
	
}
