package com.hearmysong.rest.ui.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hearmysong.rest.io.entities.User;
import com.hearmysong.rest.service.api.UserService;
import com.hearmysong.rest.ui.model.response.UserRest;


@RestController
@RequestMapping("/user")
public class LoginController {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;
	
	@GetMapping
	public User loginUser(@AuthenticationPrincipal User principal) {
		LOG.info("START");
		
		UserRest retVal = new UserRest();
		
		BeanUtils.copyProperties(principal, retVal);
		
		LOG.info("ENDE - Returning: {}", principal);
		
		return principal;
	}
	
}
