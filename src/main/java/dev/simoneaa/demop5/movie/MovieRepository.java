package dev.simoneaa.demop5.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    Optional<MovieEntity> findByTitle(String title);
    List<MovieEntity> findByGenresName(String genreName);
}
