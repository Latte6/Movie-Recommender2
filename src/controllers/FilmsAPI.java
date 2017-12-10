package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;

import model.Films;
import model.Rating;
import model.User;
import utils.FileLogger;
import utils.Serializer;

public class FilmsAPI implements FilmAPIInterface

{

	private Serializer serializer;

	private Map<Long, User> userIndex = new HashMap<Long, User>();
	private Map<String, User> fNameIndex = new HashMap<String, User>();
	private Map<Long, Films> movieIndex = new HashMap<Long, Films>();
	private Map<String, Films> titleIndex = new HashMap<String, Films>();
	private Map<Long, Rating> ratingIndex = new HashMap<>();

	Optional<User> currentUser;

	// Collection
	public Collection<User> getUsers() {
		return userIndex.values();
	}

	// Getting user by their first name
	public User getUserByfName(String fName) {
	
		return fNameIndex.get(fName);
	}

	// Getting user by their id
	public User getUser(Long id) {
		return userIndex.get(id);
	}

	@Override
	
	public Films getMovie(Long id) {
		return movieIndex.get(id);
	}

	public Films getFilmByTitle(String Title)
	{
		return titleIndex.get(Title);
	}

	public Collection<Films> getFilms() {
		return movieIndex.values();
	}

	@Override
	public Rating createRating(Long userID, Long movieID, double ratingLeft) {
		Optional<Films> movies = Optional.fromNullable(movieIndex.get(movieID));
		if (movies.isPresent()) {
			movies.get().film.add(new Rating(userID, movieID, ratingLeft));
		}
		return null;
	}

	@Override
	public Films addMovie(long id, String name, String date, String link) {
		Films movies = null;
		Optional<User> user = Optional.fromNullable(userIndex.get(id));
		if (user.isPresent()) {
			movies = new Films(name, date, link);
			user.get().movies.put(movies.FilmId, movies);
			movieIndex.put(movies.FilmId, movies);
		}
		return movies;
	}

	// Constructor
	
	public User createUser(String fName, String lName, String age, String gender, String job, String role) {
		User user = new User(fName, lName, age, gender, job, role);
		userIndex.put(user.UserId, user);
		fNameIndex.put(fName, user);
		return user;
	}

	// Constructor
	
	// Removing user by id
	@Override
	public void deleteUser(Long id) {
		User user = userIndex.remove(id);
		fNameIndex.remove(user.fName);
	}

	// Constructor
	
	// Removing user form array
	public void deleteUsers() {
		userIndex.clear();
		fNameIndex.clear();
	}

	// Constructor
	
	@SuppressWarnings("unlikely-arg-type")
	public void removeUser(String fName) {
		User user = userIndex.remove(fName);
		userIndex.remove(user.fName);
	}

	public FilmsAPI() {
	}

	// Serializer
	public FilmsAPI(Serializer serializer) {
		this.serializer = serializer;
	}

	// Exception
	@SuppressWarnings("unchecked")
	@Override
	public void load() throws Exception {
			
			serializer.read();
			userIndex = (Map<Long, User>) serializer.pop();
			fNameIndex =(Map<String, User>) serializer.pop();
			movieIndex = (Map<Long, Films>) serializer.pop();
			titleIndex = (Map<String, Films>)serializer.pop();
			ratingIndex = (Map<Long, Rating>) serializer.pop();
		
	}

	public // Exception
	void store() throws Exception {

		serializer.push(userIndex);
		serializer.push(fNameIndex);
		serializer.push(movieIndex);
		serializer.push(titleIndex);
		serializer.push(ratingIndex);
		serializer.write();
	}

	

	// Constructor

	public void initalLoad() throws IOException {
		String delims = "[|]";
		Scanner scanner = new Scanner(new File("./data/users5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 7) {
				createUser(userTokens[1], userTokens[2], userTokens[3], userTokens[4], userTokens[5], userTokens[6]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}
		scanner.close();
	}

	public Rating getRatings(Long id) {
		return ratingIndex.get(id);
	}
	// simplified login method
	  public boolean login(Long UserId, String lName) {
	    Optional<User> user = Optional.fromNullable(userIndex.get(UserId));
	    if (user.isPresent() && user.get().lName.equals(lName)) {
	      currentUser = user;
	      FileLogger.getLogger().log(currentUser.get().fName + " logged in...");
	      return true;
	    }
	    return false;
	  }
	  
	// simplified and generalized version of my logout method
	  public void logout() {
	    Optional<User> user = currentUser;
	    if (user.isPresent()) {
	      FileLogger.getLogger().log(currentUser.get().fName + " logged out...");
	      currentUser = Optional.absent();
	    }
	  }

	
	}

