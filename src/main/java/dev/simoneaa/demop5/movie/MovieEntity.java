package dev.simoneaa.demop5.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public MovieEntity() {
    }

    public MovieEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
