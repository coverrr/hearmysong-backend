package com.hearmysong.rest.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hearmysong.rest.ui.model.response.PlaylistRest;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

	private static final Logger LOG = LoggerFactory.getLogger(PlaylistController.class);

	
	@Autowired
	private OAuth2RestTemplate spotifyTemplate;
	
	@GetMapping
	public PlaylistRest getFirstPlaylist() {
		PlaylistRest retVal = new PlaylistRest();
		
		JsonNode node = this.spotifyTemplate.getForObject("https://api.spotify.com/v1/me/playlists", JsonNode.class);
		
		// TODO
		
		LOG.info(node.toString());
		
		return retVal;
	}
	
}
