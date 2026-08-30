package dev.simoneaa.demop5.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import dev.simoneaa.demop5.implementations.InterfaceGenericEditService;
import dev.simoneaa.demop5.movie.dtos.MovieDTORequest;
import dev.simoneaa.demop5.movie.dtos.MovieDTOResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "${api-endpoint}/movies")
public class MovieController {

    private final MovieGetService getService;
    private final InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse> editService;

    public MovieController(
        MovieGetService getService,
        InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse> editService
    ) { this.getService = getService;
        this.editService = editService;
    }
    
    @GetMapping("")
    public List<MovieDTOResponse> index() {
        return getService.getEntities();
    }

    @GetMapping("{id}")
    public MovieDTOResponse getById(@PathVariable Long id) {
        return getService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<MovieDTOResponse> store(@Valid @RequestBody MovieDTORequest dto) {

        MovieDTOResponse dtoResponse = editService.storeEntity(dto);

        if (dtoResponse == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.status(201).body(dtoResponse);

    }

    @PutMapping("{id}")
    public MovieDTOResponse update(@PathVariable Long id, @Valid @RequestBody MovieDTORequest dto) {
        return editService.updateEntity(id, dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        editService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public MovieDTOResponse getByTitle(@RequestParam String title) {
        return getService.getByTitle(title);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String genre
    ) {
        if (title != null) {
            return ResponseEntity.ok(getService.getByTitle(title));
        }
        if (genre != null) {
            return ResponseEntity.ok(getService.getByGenre(genre));
        }
        return ResponseEntity.badRequest().build();
    }
    
}
