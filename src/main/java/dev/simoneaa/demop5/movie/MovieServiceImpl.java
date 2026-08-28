package dev.simoneaa.demop5.movie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.simoneaa.demop5.implementations.InterfaceGenericGetService;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;
import dev.simoneaa.demop5.movie.mappers.MovieMapper;

@Service
public class MovieServiceImpl implements InterfaceGenericGetService<MovieDTOResponse>{

    private final MovieRepository repository;

    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    public List<MovieDTOResponse> getEntities() {
        List<MovieDTOResponse> movies = new ArrayList<>();

        repository.findAll().forEach(m -> {
            MovieDTOResponse movie = MovieMapper.toDTO(m);
            movies.add(movie);
        });

        return movies;
    }
    
}
