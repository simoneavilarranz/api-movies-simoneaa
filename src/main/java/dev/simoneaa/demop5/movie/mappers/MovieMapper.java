package dev.simoneaa.demop5.movie.mappers;

import dev.simoneaa.demop5.movie.MovieEntity;
import dev.simoneaa.demop5.movie.dtos.MovieDTORequest;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;

public class MovieMapper {

    public static MovieEntity toEntity(MovieDTORequest dtoRequest) {
        MovieEntity movie = new MovieEntity();
        movie.setTitle(dtoRequest.title());
        movie.setDirector(dtoRequest.director());
        movie.setLength(dtoRequest.length());
        return movie;
    }

    public static MovieDTOResponse toDTO(MovieEntity entity) {
        MovieDTOResponse dtoResponse = new  MovieDTOResponse(entity.getId(), entity.getTitle(), entity.getDirector(), entity.getLength());
        return dtoResponse;
    }
    
}
