package com.hearmysong.rest.security;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

  private static final Logger LOG = LoggerFactory.getLogger(AuthenticationSuccessListener.class);

  // FUNKTIONIERT NOCH NICHT!!!! Muss wahrscheinlich noch irgendwo registriert werden, aber wo??

  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent event) {
    Authentication auth = event.getAuthentication();
    LOG.debug("Authentication class: "+auth.getClass().toString());

    if(auth instanceof OAuth2Authentication){

        OAuth2Authentication oauth2 = (OAuth2Authentication)auth;

        @SuppressWarnings("unchecked")
        Map<String, Object> details = (Map<String, Object>)oauth2.getUserAuthentication().getDetails();         

        LOG.info("User {} logged in: {}", oauth2.getName(), details);
        LOG.info("User {} has authorities {} ", oauth2.getName(), oauth2.getAuthorities());



    } else {
    	LOG.warn("User authenticated by a non OAuth2 mechanism. Class is "+auth.getClass());
    }

  }
}