package dev.simoneaa.demop5.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import dev.simoneaa.demop5.implementations.InterfaceGenericEditService;
import dev.simoneaa.demop5.implementations.InterfaceGenericGetService;
import dev.simoneaa.demop5.movie.dtos.MovieDTORequest;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;
import dev.simoneaa.demop5.movie.exceptions.MovieExceptionNotFound;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterfaceGenericGetService<MovieDTOResponse> service;

    @MockitoBean
    private InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse> editService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex_ShouldReturnAMovie() throws Exception {
        MovieDTOResponse dto = new MovieDTOResponse(1L, "Cure", "Kiyoshi Kurosawa", 111);
        List<MovieDTOResponse> countries = new ArrayList<>();
        countries.add(dto);
        String json = mapper.writeValueAsString(countries);

        when(service.getEntities()).thenReturn(countries);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/movies"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(json);
        assertThat(response.getContentAsString()).contains("Cure");
        assertThat(response.getContentAsString()).contains("Kiyoshi Kurosawa");
        assertThat(response.getContentAsString()).contains("111");
    }

    @Test
    void testgetById_ShouldReturnAMovieById() throws Exception {
        MovieDTOResponse dto = new MovieDTOResponse(1L, "Cure", "Kiyoshi Kurosawa", 111);
        String json = mapper.writeValueAsString(dto);

        when(service.getById(1L)).thenReturn(dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/movies/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(json);
    }

    @Test
    void testgetById_ShouldFailReturnAMovieById() throws Exception {
        when(service.getById(1L)).thenThrow(new MovieExceptionNotFound("Movie not found"));

        mockMvc.perform(get("/api/v1/movies/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testPost_ShouldSave() throws Exception {
        MovieDTORequest request = new MovieDTORequest("Cure", "Kiyoshi Kurosawa", 111);
        MovieDTOResponse dtoResponse = new MovieDTOResponse(1L, "Cure", "Kiyoshi Kurosawa", 111);
        String json = mapper.writeValueAsString(request);
        String jsonResponse = mapper.writeValueAsString(dtoResponse);

        when(editService.storeEntity(Mockito.any(MovieDTORequest.class))).thenReturn(dtoResponse);

        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/movies")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse);
    }

    @Test
    void testPut_ShouldUpdate() throws Exception {
        MovieDTORequest request = new MovieDTORequest("Cure", "Kiyoshi Kurosawa", 111);
        MovieDTOResponse dtoResponse = new MovieDTOResponse(1L, "Cure", "Kiyoshi Kurosawa", 111);
        String json = mapper.writeValueAsString(request);
        String jsonResponse = mapper.writeValueAsString(dtoResponse);

        when(editService.updateEntity(Mockito.eq(1L), Mockito.any(MovieDTORequest.class))).thenReturn(dtoResponse);

        MockHttpServletResponse response = mockMvc.perform(put("/api/v1/movies/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse);
    }

    @Test
    void testDelete_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/1"))
            .andExpect(status().isNoContent());
    }
}
