package dev.simoneaa.demop5.movie.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import dev.simoneaa.demop5.movie.MovieEntity;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;

public class MovieMapperTest {
    
    @Test
    public void toDTOTest() {
        MovieEntity entity = new MovieEntity(1L, "Inception", "Christopher Nolan", 148);
        MovieDTOResponse response = MovieMapper.toDTO(entity);
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.title()).isEqualTo("Inception");
        assertThat(response.director()).isEqualTo("Christopher Nolan");
        assertThat(response.length()).isEqualTo(148);
    }

}
