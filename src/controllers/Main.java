package controllers;

import java.io.File;
import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import controllers.FilmAPIInterface;
import model.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main implements ShellDependent {
  private static final String ADMIN = "admin";
  public FilmsAPI fuAPI;
  private Shell theShell;
  public FilmAPIInterface films;

  public Main() throws Exception {
    File datastore = new File("datastore.xml");
    Serializer serializer = new XMLSerializer(datastore);
    fuAPI = new FilmsAPI(serializer);
    if (datastore.isFile()) {
      fuAPI.load();
    }
  }

  public void cliSetShell(Shell theShell) {
    this.theShell = theShell;
  }

  @Command(description = "Log in")
  public void logIn(@Param(name = "user name") Long UserId, @Param(name = "password") String lName)
      throws IOException {

    if (fuAPI.login(UserId, lName) && fuAPI.currentUser.isPresent()) {
      User user = fuAPI.currentUser.get();
      System.out.println("You are logged in as " + user.fName);
      if (user.role!=null && user.role.equals(ADMIN)) {
        AdminMenu adminMenu = new AdminMenu(fuAPI, user.fName);
        ShellFactory.createSubshell(user.fName, theShell, "Admin", adminMenu).commandLoop();
      } else {
        DefaultMenu defaultMenu = new DefaultMenu(fuAPI, user);
        ShellFactory.createSubshell(user.fName, theShell, "Default", defaultMenu).commandLoop();
      }
    } else
      System.out.println("Unknown username/last name.");
  }

  public static void main(String[] args) throws Exception {
    Main main = new Main();
    main.fuAPI.initalLoad();
    Shell shell = ShellFactory.createConsoleShell("pm", "Welcome Film recommender - ?list for instructions",
        main);
    shell.commandLoop();
    main.fuAPI.store();
  }
}









/*
package controllers;

import java.io.File;
import java.util.Collection;
import com.google.common.base.Optional;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.ShellFactory;
import model.Films;
import model.Rating;
import model.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main
{
	public FilmsAPI fuAPI;
	
	public Main() throws Exception {
		File datastore = new File ("datastore1.xml");
		Serializer serializer = new XMLSerializer(datastore);
		fuAPI = new FilmsAPI(serializer);
		if(datastore.isFile())
		{
			fuAPI.load(datastore);
		}
	}
	public static void main(String[] args) throws Exception
	{
		Main main = new Main();
		ShellFactory.createConsoleShell("Type in command", "Welcome to the Film Picker program", main).commandLoop();
		main.fuAPI.store();
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
	  @Param(name="job")  String job, @Param(name="password")  String password, @Param(name="role")  String role)
	  {
		fuAPI.createUser(firstName, lastName, age, gender, job, password, role);
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
	@Command(description="Get Film by a title")
	public void getFilmByTitle(@Param(name="Title") String Title)
	{
		Films film = fuAPI.getFilmByTitle(Title);
		System.out.println(film);
	}

}
	
	

*/