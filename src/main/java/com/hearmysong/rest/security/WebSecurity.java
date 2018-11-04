package com.hearmysong.rest.security;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableOAuth2Client
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private static final Logger LOG = LoggerFactory.getLogger(WebSecurity.class);

	@Autowired
	OAuth2ClientContext clientContext;
	
	@Autowired
	private PrincipalExtractor spotifyPrincipleExtractor;
	
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		http
	      .antMatcher("/**")
	      .authorizeRequests()
	        .antMatchers("/", "/login**", "/webjars/**", "/error**")
	        .permitAll()
	      .anyRequest()
	        .authenticated()
	      .and()
	      	.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}
	
	
	@Bean
	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(
	    OAuth2ClientContextFilter filter) {
	  FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
	  registration.setFilter(filter);
	  registration.setOrder(-100);
	  return registration;
	}

	@Bean
	@ConfigurationProperties("spotify.client")
	public AuthorizationCodeResourceDetails spotify() {
		return new AuthorizationCodeResourceDetails();
	}
	
	@Bean
	@ConfigurationProperties("spotify.resource")
	public ResourceServerProperties spotifyResource() {
		return new ResourceServerProperties();
	}
	
	@Bean 
	public PrincipalExtractor principalExtractor() {
		return new SpotifyPrincipalExtractor();
	}
	
	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
		return new OAuth2RestTemplate(resource, context);
	}
	
	
	private Filter ssoFilter() {
		OAuth2ClientAuthenticationProcessingFilter spotifyFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/spotify");
		OAuth2RestTemplate spotifyTemplate = new OAuth2RestTemplate(spotify(), this.clientContext);
		
		spotifyFilter.setRestTemplate(spotifyTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(spotifyResource().getUserInfoUri(), spotify().getClientId());
		tokenServices.setRestTemplate(spotifyTemplate);
		tokenServices.setPrincipalExtractor(this.spotifyPrincipleExtractor);
		
		spotifyFilter.setTokenServices(tokenServices);
		
		return spotifyFilter;
	}
}