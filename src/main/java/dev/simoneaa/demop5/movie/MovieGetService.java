package dev.simoneaa.demop5.movie;

import dev.simoneaa.demop5.implementations.InterfaceGenericGetService;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;
import java.util.List;

public interface MovieGetService extends InterfaceGenericGetService<MovieDTOResponse> {
    MovieDTOResponse getByTitle(String title);
    List<MovieDTOResponse> getByGenre(String genreName);
}