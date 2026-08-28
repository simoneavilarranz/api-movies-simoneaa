package dev.simoneaa.demop5.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {
    @InjectMocks
    private MovieServiceImpl service;

    @Mock
    private MovieRepository repository;

    @BeforeEach
    void setUp() {
        service = new MovieServiceImpl(repository);
    }

    @Test
    void testGetEntities() {
        List<MovieEntity> moviesMock = List.of(
                new MovieEntity(1L, "Inception", "Christopher Nolan", 148),
                new MovieEntity(2L, "Cure", "Kiyoshi Kurosawa", 111)
        );

        when(repository.findAll()).thenReturn(moviesMock);
        List<MovieDTOResponse> movies = service.getEntities();

        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).title()).isEqualTo("Inception");
        assertThat(movies.get(0).director()).isEqualTo( "Christopher Nolan");
        assertThat(movies.get(0).length()).isEqualTo(148);
        assertThat(movies.get(1).title()).isEqualTo("Cure");
        assertThat(movies.get(1).director()).isEqualTo( "Kiyoshi Kurosawa");
        assertThat(movies.get(1).length()).isEqualTo(111);
    }

    @Test
    void testGetById() {
        MovieEntity countryMock = new MovieEntity(1L, "Cure", "Kiyoshi Kurosawa", 111);

        when(repository.findById(1L)).thenReturn(Optional.of(countryMock));
        MovieDTOResponse movies = service.getById(1L);

        assertThat(movies.id()).isEqualTo(1L);
        assertThat(movies.title()).isEqualTo("Cure");
        assertThat(movies.director()).isEqualTo("Kiyoshi Kurosawa");
        assertThat(movies.length()).isEqualTo(111);
    }
}
