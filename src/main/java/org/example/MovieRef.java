package org.example;

import java.util.List;

public class MovieRef {
    private String _id;
    private String title;
    private int year;
    private String genre;
    private String summary;
    private String country;
    private Artist director; // Assuming the director is of type Artist
    private List<String> actors; // Assuming actors are stored as a list of strings (IDs)

    // Constructor
    public MovieRef(String _id, String title, int year, String genre, String summary,
                    String country, Artist director, List<String> actors) {
        this._id = _id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.summary = summary;
        this.country = country;
        this.director = director;
        this.actors = actors;
    }

    // Getters and Setters
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Artist getDirector() {
        return director;
    }

    public void setDirector(Artist director) {
        this.director = director;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}
