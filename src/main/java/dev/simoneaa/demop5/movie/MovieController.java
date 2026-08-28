package dev.simoneaa.demop5.movie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import dev.simoneaa.demop5.implementations.InterfaceGenericGetService;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;

@RestController
@RequestMapping(path = "${api-endpoint}/movies")
public class MovieController {

    private final InterfaceGenericGetService<MovieDTOResponse> getService;

    public MovieController(
        InterfaceGenericGetService<MovieDTOResponse> getService
    ) { this.getService = getService;
    }
    
    @GetMapping("")
    public List<MovieDTOResponse> index() {
        return getService.getEntities();
    }

    @GetMapping("{id}")
    public MovieDTOResponse getById(@PathVariable Long id) {
        return getService.getById(id);
    }
    
}
