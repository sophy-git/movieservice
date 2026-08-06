package com.example.movieservice.movie.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.movieservice.movie.entity.Movie;
import com.example.movieservice.movie.repository.MovieRepository;

@Service
@Transactional(readOnly = true)
public class MovieService {

	private final MovieRepository movieRepository;

	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Transactional
	public Movie createMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	public Movie getMovieById(Long movieId) {
		return movieRepository.findById(movieId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found: " + movieId));
	}

	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Transactional
	public Movie updateMovie(Long movieId, Movie request) {
		Movie movie = getMovieById(movieId);

		if (request.getTitle() != null) {
			movie.setTitle(request.getTitle());
		}
		if (request.getProducer() != null) {
			movie.setProducer(request.getProducer());
		}
		if (request.getPrice() != null) {
			movie.setPrice(request.getPrice());
		}
		if (request.getOpenDate() != null) {
			movie.setOpenDate(request.getOpenDate());
		}

		return movie;
	}

	@Transactional
	public void deleteMovie(Long movieId) {
		if (!movieRepository.existsById(movieId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found: " + movieId);
		}
		movieRepository.deleteById(movieId);
	}
}
