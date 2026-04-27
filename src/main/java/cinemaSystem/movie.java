package cinemaSystem;

import java.util.Date;

public class movie {

public class Movie {
private int movieID;
private String movie_name;
private String description;
private int duration;
private double rate;
private String trailerurl;
private Date releaseDate;
private String image_URL;

public String getImage_URL() {
	return image_URL;
}
public void setImage_Url(String image_URL) {
	this.image_URL = image_URL;
}
public Date getReleaseDate() {
	return releaseDate;
}
public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
}
private String genre;


public String getGenre() {
	return genre;
}
public void setGenre(String genre) {
	this.genre = genre;
}
public int getMovieID() {
	return movieID;
}
public void setMovieID(int movieID) {
	this.movieID = movieID;
}
public String getMovie_name() {
	return movie_name;
}
public void setMovie_name(String movie) {
	this.movie_name = movie;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public double getRate() {
	return rate;
}
public void setRate(double rate) {
	this.rate = rate;
}
public String getTrailerurl() {
	return trailerurl;
}
public void setTrailerurl(String trailerurl) {
	this.trailerurl = trailerurl;
}

} }

