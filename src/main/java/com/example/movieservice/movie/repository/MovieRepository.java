package com.example.movieservice.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movieservice.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
