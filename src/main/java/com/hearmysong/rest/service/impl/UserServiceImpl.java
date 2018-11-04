package com.hearmysong.rest.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hearmysong.rest.dto.UserDTO;
import com.hearmysong.rest.io.entities.User;
import com.hearmysong.rest.repository.UserRepository;
import com.hearmysong.rest.service.api.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDTO createUser(UserDTO user) {
		User userEntity = new User();
		
		BeanUtils.copyProperties(user, userEntity);
		
		
		User savedUser = this.userRepository.save(userEntity);
		
		UserDTO retVal = new UserDTO();
		
		BeanUtils.copyProperties(savedUser, retVal);
		
		return retVal;
		
	}


	
	
}
