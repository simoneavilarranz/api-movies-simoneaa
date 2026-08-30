package dev.simoneaa.demop5.movie;

import dev.simoneaa.demop5.implementations.InterfaceGenericGetService;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;

public interface MovieGetService extends InterfaceGenericGetService<MovieDTOResponse> {
    MovieDTOResponse getByTitle(String title);
}