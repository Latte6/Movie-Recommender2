package controllers;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import model.Rating;
import model.User;

public class DefaultMenu {

  private String name;
  private User user;
  private FilmsAPI fuAPI; 
  public DefaultMenu(FilmsAPI fuAPI, User user) {
    this.fuAPI = fuAPI;
    this.setName(user.fName);
    this.user = user;
  }
  @Command(description = "Get a Users detail")
  public void getUser(@Param(name = "First Name") String fName) {
    User user = fuAPI.getUserByfName(fName);
    System.out.println(user);
  }
  @Command(description = "Add an rating")
  public void addRating(@Param(name = "User Unique Identifier") Long userId, @Param(name = "Movie Unique Identifier") Long filmId,
      @Param(name = "rating") double ratingLeft) {
    fuAPI.createRating(userId, filmId, ratingLeft);
  }
  @Command(description = "Add Location to an activity")
  public void addFilm(@Param(name = "Film Id") Long Id, @Param(name = "url") String url,
      @Param(name = "Title") String title, @Param(name = "Realese Date") String date) {
    Optional<Rating> rating = Optional.fromNullable(fuAPI.getRatings(Id));
    if (rating.isPresent()) {
      fuAPI.addMovie(rating.get().Id, title, date, url);
    }
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