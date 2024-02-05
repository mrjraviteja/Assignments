package com.mrj.learningportal.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class RegistrationEntity {
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity userEntity;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private CourseEntity courseEntity;
	
	@JsonFormat(pattern="MM/dd/yyyy HH:mm")
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	@CreatedDate
	private LocalDateTime registeredDate;

}