package dev.simoneaa.demop5.movie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import dev.simoneaa.demop5.implementations.InterfaceGenericEditService;
import dev.simoneaa.demop5.implementations.InterfaceGenericGetService;
import dev.simoneaa.demop5.movie.dtos.MovieDTORequest;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;
import dev.simoneaa.demop5.movie.exceptions.MovieExceptionNotFound;
import dev.simoneaa.demop5.movie.mappers.MovieMapper;

@Service
public class MovieServiceImpl implements MovieGetService,
InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse> {

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

    public MovieDTOResponse getByTitle(String title) {
        MovieEntity movie = repository.findByTitle(title)
            .orElseThrow(() -> new MovieExceptionNotFound("Movie not found. Title " + title + " does not exist"));
        return MovieMapper.toDTO(movie);
    }
 
    @Override
    public MovieDTOResponse getById(Long id) {
        MovieEntity movie = repository.findById(id)
        .orElseThrow(() -> new MovieExceptionNotFound("Movie not found. Id " + id + " does not exist"));
        return MovieMapper.toDTO(movie);
    }

    @Override
    public MovieDTOResponse storeEntity(MovieDTORequest dto) {
        MovieEntity movieToSave = MovieMapper.toEntity(dto);
        Example<MovieEntity> example = Example.of(movieToSave);
        boolean isEmpty = repository.findAll(example).isEmpty();

        if (!isEmpty)
            return null;

            MovieEntity movieSaved = repository.save(movieToSave);

            return MovieMapper.toDTO(movieSaved);
    }

    @Override
    public MovieDTOResponse updateEntity(Long id, MovieDTORequest dto) {
        MovieEntity movie = repository.findById(id)
        .orElseThrow(() -> new MovieExceptionNotFound("Movie not found. Id " + id + " does not exist"));
        movie.setTitle(dto.title());
        movie.setDirector(dto.director());
        movie.setLength(dto.length());

        MovieEntity movieUpdated = repository.save(movie);
        return MovieMapper.toDTO(movieUpdated);
    }

    @Override
    public void deleteEntity(Long id) {
        MovieEntity movie = repository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound("Movie not found. Id " + id + " does not exist"));
        repository.delete(movie);
    }

}
