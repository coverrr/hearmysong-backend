package com.hearmysong.rest.io.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "users")
public class User implements Serializable{
	private static final long serialVersionUID = 704263032247211825L;

	/**
	 * ID fuer Datenbank
	 */
	@Id
	@GeneratedValue
	private long id;
	
	/**
	 * Spotify-ID, ueber die in Spring die Authentifizierung laueft
	 */
	@Column(nullable = false, length = 200, unique = true)
	private String spotifyId;

	/**
	 * Angezeigter kompletter Name, wenn vorhanden
	 */
	@Column(nullable = false, length = 120)
	private String displayName;
	
	@Column(nullable = true, length = 120)
	private String email;
	
	/**
	 * Profilbild
	 */
	@Column(nullable = true)
	private String imageUrl;
	
	/**
	 * z.B. DE fuer Deutschland
	 */
	@Column(nullable = true, length = 4)
	private String countryCode;
	
	/**
	 * Typ des Spotify-Accounts, z.B. PREMIUM
	 */
	@Column(nullable = true, length = 10)
	private String product;
	
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", spotifyId=" + spotifyId + ", displayName=" + displayName + ", email=" + email
				+ ", imageUrl=" + imageUrl + ", countryCode=" + countryCode + ", product=" + product + "]";
	}

	

}
