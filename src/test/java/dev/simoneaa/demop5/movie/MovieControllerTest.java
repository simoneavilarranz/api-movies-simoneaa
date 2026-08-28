package dev.simoneaa.demop5.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.simoneaa.demop5.implementations.InterfaceGenericGetService;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterfaceGenericGetService<MovieDTOResponse> service;

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
}
