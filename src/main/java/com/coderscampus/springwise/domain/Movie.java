package com.coderscampus.springwise.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String rating;
    private int yearOf;



	public int getYearOf() {
		return yearOf;
	}

	public void setYearOf(int yearOf) {
		this.yearOf = yearOf;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

  

   

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", genre=" + genre + ", rating=" + rating + ", yearOf=" + yearOf
				+ "]";
	}

	public Movie(String name, String genre, String rating, int yearOf) {
        super();
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.yearOf = yearOf;
    }

    public Movie() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Movie(String name, String genre, int yearOf) {
        super();
        this.name = name;
        this.genre = genre;
        this.yearOf = yearOf;
    }
}
