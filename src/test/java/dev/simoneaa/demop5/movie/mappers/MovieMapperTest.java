package dev.simoneaa.demop5.movie.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import dev.simoneaa.demop5.movie.MovieEntity;
import dev.simoneaa.demop5.movie.dtos.MovieDTORequest;
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

    @Test
    public void toEntityTest() {
        MovieDTORequest request = new MovieDTORequest("Inception", "Christopher Nolan", 148);
        MovieEntity entity = MovieMapper.toEntity(request);
        assertThat(entity.getTitle()).isEqualTo("Inception");
        assertThat(entity.getDirector()).isEqualTo("Christopher Nolan");
        assertThat(entity.getLength()).isEqualTo(148);
    }

}
