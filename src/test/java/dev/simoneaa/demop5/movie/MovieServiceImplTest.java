package dev.simoneaa.demop5.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import dev.simoneaa.demop5.movie.dtos.MovieDTORequest;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;
import dev.simoneaa.demop5.movie.exceptions.MovieExceptionNotFound;

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
        MovieEntity movieMock = new MovieEntity(1L, "Cure", "Kiyoshi Kurosawa", 111);

        when(repository.findById(1L)).thenReturn(Optional.of(movieMock));
        MovieDTOResponse movies = service.getById(1L);

        assertThat(movies.id()).isEqualTo(1L);
        assertThat(movies.title()).isEqualTo("Cure");
        assertThat(movies.director()).isEqualTo("Kiyoshi Kurosawa");
        assertThat(movies.length()).isEqualTo(111);
    }

    @Test
    void testFailGetById() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getById(1L))
        .isInstanceOf(MovieExceptionNotFound.class);
    }

    @Test
    void testStoreMovie() {
        MovieDTORequest request = new MovieDTORequest("Inception", "Christopher Nolan", 148);

        when(repository.save(Mockito.any(MovieEntity.class))).thenReturn(new MovieEntity(1L, "Inception", "Christopher Nolan", 148));
        when(repository.findAll(Mockito.<Example<MovieEntity>>any())).thenReturn(List.of());
        MovieDTOResponse entity = service.storeEntity(request);

        assertThat(entity.title()).isEqualTo("Inception");
        assertThat(entity.director()).isEqualTo("Christopher Nolan");
        assertThat(entity.length()).isEqualTo(148);
    }

    @Test
    void testServiceUpdate() {
        MovieDTORequest request = new MovieDTORequest("Inception", "Christopher Nolan", 148);
        MovieEntity movieExistente = new MovieEntity(1L, "Vieja", "Director Viejo", 100);
        MovieEntity movieActualizada = new MovieEntity(1L, "Inception", "Christopher Nolan", 148);

        when(repository.findById(1L)).thenReturn(Optional.of(movieExistente));
        when(repository.save(Mockito.any(MovieEntity.class))).thenReturn(movieActualizada);
        MovieDTOResponse entity = service.updateEntity(1L, request);

        assertThat(entity.title()).isEqualTo("Inception");
        assertThat(entity.director()).isEqualTo("Christopher Nolan");
        assertThat(entity.length()).isEqualTo(148);
    }
}
