package dev.simoneaa.demop5.movie.mappers;

import dev.simoneaa.demop5.movie.MovieEntity;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;

public class MovieMapper {

    public static MovieDTOResponse toDTO(MovieEntity entity) {
        MovieDTOResponse dtoResponse = new  MovieDTOResponse(entity.getId(), entity.getTitle(), entity.getDirector(), entity.getLength());
        return dtoResponse;
    }
    
}
