package dev.simoneaa.demop5.movie.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieDTORequest(
    @NotBlank(message = "Title cannot be empty")
    String title,

    @NotBlank(message = "Director cannot be empty")
    String director,

    @NotNull(message = "Length cannot be null")
    Integer length
) {
}
