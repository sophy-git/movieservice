package com.example.movieservice.movie.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movieservice.movie.entity.Movie;
import com.example.movieservice.movie.service.MovieService;

@RestController
@RequestMapping("/movies")
@Validated
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
		Movie created = movieService.createMovie(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping
	public List<Movie> getAllMovies() {
		return movieService.getAllMovies();
	}

	@GetMapping("/{movieId}")
	public Movie getMovie(@PathVariable Long movieId) {
		return movieService.getMovieById(movieId);
	}

	@PutMapping("/{movieId}")
	public Movie updateMovie(@PathVariable Long movieId, @RequestBody Movie movie) {
		return movieService.updateMovie(movieId, movie);
	}

	@DeleteMapping("/{movieId}")
	public ResponseEntity<Void> deleteMovie(@PathVariable Long movieId) {
		movieService.deleteMovie(movieId);
		return ResponseEntity.noContent().build();
	}
}
