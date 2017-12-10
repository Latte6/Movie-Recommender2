package controllers;

import java.util.Collection;

import model.Films;
import model.Rating;
import model.User;

public interface FilmAPIInterface {
	 User createUser(String fName, String lName, String age, String gender, String job, String role ); 
	 void deleteUser(Long userID);
	 Films addMovie(long id, String name, String date, String link);
	 Rating createRating(Long ID, Long filmID, double rating);
	Films getMovie(Long iD);
	Films getFilmByTitle(String Title);
	boolean login(Long userID, String lName);

	void logout();
	Collection<User> getUsers();

	
	
	public void load() throws Exception;
	public void store() throws Exception;
	
	
}
