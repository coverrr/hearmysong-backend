package com.hearmysong.rest.security;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import com.hearmysong.rest.io.entities.User;
import com.hearmysong.rest.repository.UserRepository;

public class SpotifyPrincipalExtractor implements PrincipalExtractor{

	private static final Logger LOG = LoggerFactory.getLogger(SpotifyPrincipalExtractor.class);
	
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		LOG.info("INIT - PostConstruct Bean");
	}
	
	@Override
	public Object extractPrincipal(Map<String, Object> map) {
		LOG.info("START");
	
		String principalId = (String) map.get("id");
		
		User user = userRepository.findBySpotifyId(principalId);
		 
		 if(user == null) {
			 LOG.info("No user found in database, creating new user with principal-ID -> {}", principalId);
			 user = new User();
			 user.setSpotifyId(principalId);
			 user.setDisplayName((String) map.get("display_name"));
			 user.setEmail((String)map.get("email"));
			 user.setCountryCode((String)map.get("country"));
			 user.setProduct((String)map.get("product"));
			 
			 // URL des Profilbilds // TODO: ExceptionHandling
			 ArrayList<Map<String, Object>> images = (ArrayList<Map<String, Object>>)map.get("images");
			 String imgUrl = (String)images.get(0).get("url");
			 
			 user.setImageUrl(imgUrl);
			 // TODO: LastLoginTime + CreatedAt
			 
			 this.userRepository.save(user);
			 
		 } else {
			 LOG.info("User found with principal-ID -> {}", principalId);
			 // user.setLastLoginTime(LocalDateTime.now());
		 }
		 return user;
	}

}
