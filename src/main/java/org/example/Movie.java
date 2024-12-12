package org.example;

import java.util.List;

public class Movie {

    private String id;
    private String title;
    private int year;
    private String genre;
    private String summary;
    private String country;
    private String director;
    private List<Actor> actors; // Assuming you might have an Actor class

    // Constructor
    public Movie(String id, String title, int year, String genre, String summary, String country, String director, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.summary = summary;
        this.country = country;
        this.director = director;
        this.actors = actors;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    // Example Director class
    public static class Director {
        private String id;
        private String lastName;
        private String firstName;
        private String birthDate;

        // Constructor
        public Director(String id, String lastName, String firstName, String birthDate) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.birthDate = birthDate;
        }

        // Getters and setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }
    }

    // Example Actor class (you can expand this based on your needs)
    public static class Actor {
        private String id;
        private String name;

        // Constructor
        public Actor(String id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
