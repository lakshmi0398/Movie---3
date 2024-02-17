
package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.movie.model.Movie;
import com.example.movie.repository.MovieJpaRepository;
import com.example.movie.repository.MovieRepository;
import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

import java.util.*;

// Do not modify the below code
@Service
public class MovieJpaService implements MovieRepository {

  @Autowired
  public MovieJpaRepository db;

  // Do not modify the above code

  // Write your code here

  @Override
  public ArrayList<Movie> getMovies() {
    Collection<Movie> movieCollection = db.findAll();
    ArrayList<Movie> movies = new ArrayList<>(movieCollection);
    return movies;
  }

  @Override
  public Movie addMovies(Movie movie) {
    db.save(movie);
    return movie;

  }

  @Override
  public Movie getMovieById(int movieId) {
    try {
      Movie movie = db.findById(movieId).get();

      return movie;

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

  }

  @Override
  public Movie updateMovie(int movieId, Movie movie) {
    try {
      Movie newmovie = db.findById(movieId).get();

      if (movie.getLeadActor() != null) {
        newmovie.setLeadActor(movie.getLeadActor());

      }
      if (movie.getMovieName() != null) {
        newmovie.setMovieName(movie.getMovieName());
      }
      db.save(newmovie);
      return newmovie;

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

  }

  @Override
  public void deleteMovie(int movieId) {
    try {
      db.deleteById(movieId);

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

}