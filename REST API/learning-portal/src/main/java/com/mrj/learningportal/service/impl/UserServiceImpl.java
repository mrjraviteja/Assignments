package com.mrj.learningportal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mrj.learningportal.entity.UserEntity;
import com.mrj.learningportal.repository.UserRepository;
import com.mrj.learningportal.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;

	@Override
	public List<UserEntity> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserEntity> findUserById(Long id) {
		return userRepository.findById(id);
	}
	
	@Override
	public UserEntity addUser(UserEntity userEntity)
	{
		return userRepository.save(userEntity);
	}
	
}
