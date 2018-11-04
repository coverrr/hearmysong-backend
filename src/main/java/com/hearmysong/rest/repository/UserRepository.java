package com.hearmysong.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hearmysong.rest.io.entities.User;

// UserRepository extends CrudRepository<User, Long> {...}
// createUser, retrieveUser, updateUser, deleteUser -> nun sofort verfügbar
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findBySpotifyId(String spotifyId);
	
	//User findUserByUserId(String userId);	// wird automatisch implementiert, da userId ein Attribut in der Enitätsklasse ist
}
