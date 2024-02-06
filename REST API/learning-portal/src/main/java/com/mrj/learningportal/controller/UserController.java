package com.mrj.learningportal.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrj.learningportal.dto.UserRequestDto;
import com.mrj.learningportal.dto.UserResponseDto;
import com.mrj.learningportal.entity.CourseEntity;
import com.mrj.learningportal.entity.RegistrationEntity;
import com.mrj.learningportal.entity.UserEntity;
import com.mrj.learningportal.service.CourseService;
import com.mrj.learningportal.service.RegistrationService;
import com.mrj.learningportal.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	private RegistrationService registrationService;
	private CourseService courseService;
	
	@GetMapping
	public ResponseEntity<Object> showAllUsers()
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
	}
	
	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody UserRequestDto userRequestDto)
	{
		UserEntity userEntity = new UserEntity();
		userEntity.setName(userRequestDto.getName());
		userEntity.setRole(userRequestDto.getRole());
		UserEntity user = userService.addUser(userEntity);
		if(user == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Failed to add User");
		}
		
		RegistrationEntity registrationEntity = new RegistrationEntity();
		registrationEntity.setUserEntity(user);
		registrationService.saveRegistration(registrationEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> showUserById(@PathVariable(value = "id") Long id)
	{
		Optional<UserEntity> userEntity = userService.findUserById(id);
		if(userEntity.isPresent())
		{
			UserEntity user = userEntity.get();
			UserResponseDto userresp = new UserResponseDto();
			userresp.setId(user.getId());
			userresp.setName(user.getName());
			userresp.setRole(user.getRole());
			userresp.setEnrolledCourses(registrationService.findEnrolledCoursesByUser(user));
			return ResponseEntity.status(HttpStatus.FOUND).body(userresp);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}
	
	@PostMapping("/{id}/enroll/{courseid}")
	public ResponseEntity<Object> enrollCourse(@PathVariable(value="id") Long id,@PathVariable(value="courseid") Long courseId)
	{
		try {
			Optional<UserEntity> userEntity = userService.findUserById(id);
			Optional<CourseEntity> courseEntity = courseService.findCourseById(courseId);
			
			if(userEntity.isPresent() && courseEntity.isPresent())
			{
				UserEntity user = userEntity.get();
				CourseEntity course = courseEntity.get();
				
				if(registrationService.checkRegistrationByUserAndCourse(user, course))
				{
					return ResponseEntity.badRequest().body("User is already enrolled in the course.");
				}
				else if(registrationService.checkRegistrationByUser(user))
				{
					List<RegistrationEntity> registrations = registrationService.findRegistrationByUserEntity(user);
					Optional<RegistrationEntity> registrationOpt = registrations.stream().filter(reg -> reg.getCourseEntity() == null).findFirst();
					RegistrationEntity registrationEntity;
					if(registrationOpt.isEmpty())
					{
						registrationEntity = new RegistrationEntity();
						registrationEntity.setUserEntity(user);
					}
					else
					{
						registrationEntity = registrationOpt.get();
					}
					registrationEntity.setCourseEntity(course);
					registrationService.saveRegistration(registrationEntity);
					
					UserResponseDto userresp = new UserResponseDto();
					userresp.setId(user.getId());
					userresp.setName(user.getName());
					userresp.setRole(user.getRole());
					userresp.setEnrolledCourses(registrationService.findEnrolledCoursesByUser(user));
					return ResponseEntity.status(HttpStatus.ACCEPTED).body(userresp);
				}
				else
				{
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
				}
			}
			else
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Course not found!");
			}
			
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enrolling user in the course.");
		}
	}
	
	@PostMapping("/{id}/favourite/{courseid}")
	public ResponseEntity<Object> favouriteCourse(@PathVariable(value="id") Long id,@PathVariable(value="courseid") Long courseId)
	{
		Optional<UserEntity> userEntity = userService.findUserById(id);
		Optional<CourseEntity> courseEntity = courseService.findCourseById(courseId);
		
		if(userEntity.isPresent() && courseEntity.isPresent())
		{
			UserEntity user = userEntity.get();
			CourseEntity course = courseEntity.get();
			
			if(registrationService.checkRegistrationByUserAndCourse(user, course))
			{
				UserResponseDto userresp = new UserResponseDto();
				userresp.setId(user.getId());
				userresp.setName(user.getName());
				userresp.setRole(user.getRole());
				userresp.setEnrolledCourses(user.getEnrolledCourses().stream().map(pred -> pred.getCourseEntity()).collect(Collectors.toList()));
				List<CourseEntity> favcourses = user.getFavouriteCourses().stream().map(pred -> pred.getCourseEntity()).collect(Collectors.toList());
				favcourses.add(course);
				userresp.setFavouriteCourses(favcourses);
				return ResponseEntity.status(HttpStatus.OK).body(userresp);
			}
			else
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not enrolled in the course");
			}
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Course not found!");
		}
	}
	
	
}
