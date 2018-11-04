package com.hearmysong.rest.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = -4965890108295136916L;
	
	// TODO: muss aktualisiert werden
	
	/**
	 * ID für Datenbank
	 */
	private long id;
	
	/**
	 * Public User-ID für Datenaustausch zwischen Front- und Backend.
	 */
	private String userId; 
	
	/**
	 * Von Spotify vergebene öffentliche ID.
	 */
	private String spotifyId;
	
	/**
	 * Kompletter Name aus Spotify.
	 */
	private String name;
	
	private String profileUrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getSpotifyId() {
		return spotifyId;
	}

	public void setSpotifyId(String spotifyId) {
		this.spotifyId = spotifyId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
