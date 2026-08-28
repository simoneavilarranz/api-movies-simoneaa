package dev.simoneaa.demop5.mapper;

import dev.simoneaa.demop5.dtos.MovieDTOResponse;
import dev.simoneaa.demop5.movie.MovieEntity;

public class MovieMapper {

    public static MovieDTOResponse toDTO(MovieEntity entity) {
        MovieDTOResponse dtoResponse = new  MovieDTOResponse(entity.getId(), entity.getTitle(), entity.getDirector(), entity.getLength());
        return dtoResponse;
    }
    
}
