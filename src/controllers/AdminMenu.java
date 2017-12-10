package controllers;

import java.util.Collection;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import model.Films;
import model.Rating;
import model.User;

public class AdminMenu {
	private String name;
	private FilmsAPI fuAPI;
	public AdminMenu(FilmsAPI fuAPI, String userName) {
		this.fuAPI = fuAPI;
		this.setName(userName);
	}
	@Command(description="Add Rating")
	public void addRatings (@Param(name="UserId")  Long   UserId,       @Param(name="movieId") Long movieId, 
	                           @Param(name="rateingLeft") double rateingLeft)
	  {
	    Optional<User> user = Optional.fromNullable(fuAPI.getUser(UserId));
	    if (user.isPresent())
	    {
	    	fuAPI.createRating(UserId, movieId, rateingLeft);
	    }
	  }
	@Command(description="Delete a User")
	public void deleteUser (@Param(name="Name of the user") String fName)
	  {
	    Optional<User> user = Optional.fromNullable(fuAPI.getUserByfName(fName));
	    if (user.isPresent())
	    {
	    	fuAPI.deleteUser(user.get().UserId);
	    }
	  }
	@Command(description="Get all Users")
	//Getting all users
	public void getAllUsers() {
		Collection<User> user = fuAPI.getUsers();
		System.out.println(user);
	}
	@Command(description="Get all Films")
	//Getting all users
	public void getAllFilms() {
		Collection<Films> films = fuAPI.getFilms();
		System.out.println(films);
	}
	@Command(description="Get a User by first Name")
	public void getUser (@Param(name="fName") String fName)
	  {
		Optional<User> user =  Optional.fromNullable(fuAPI.getUserByfName(fName));
		if(user.isPresent())
		{
	    User user1 = fuAPI.getUserByfName(fName);
	    System.out.println(user1);
		}
		else
		{
			System.out.println("User not in the system");
			System.out.println("");
		}
		
		}
	@Command(description="Create a user")
	public void createUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName, 
	                          @Param(name="age")      String age,     @Param(name="gender")  String gender,
	  @Param(name="job")  String job, @Param(name="password") String role)
	  {
		fuAPI.createUser(firstName, lastName, age, gender, job, role);
	  }
	@Command(description="Add Movie to an rating")
    public void addFilm (@Param(name="Rating Id")  Long  Id, @Param(name="url")  String  url,   
	                           @Param(name="Title")     String title, @Param(name="Date Out") String date)
	  {
	    Optional<Rating> rating = Optional.fromNullable(fuAPI.getRatings(Id));
	    if (rating.isPresent())
	    {
	    	fuAPI.addMovie(rating.get().Id, title, date, url);
	    }
	  }
	@Command(description="Get Film by id")
	public void getFilmByTitle(@Param(name="Title") String Title)
	{
		Films film = fuAPI.getFilmByTitle(Title);
		System.out.println(film);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Command(description = "Save")
	public void save()throws Exception {
	fuAPI.store();
	}
}

