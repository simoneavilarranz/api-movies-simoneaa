package dev.simoneaa.demop5.movie;

import java.util.HashSet;
import java.util.Set;

import dev.simoneaa.demop5.actor.ActorEntity;
import dev.simoneaa.demop5.genre.GenreEntity;
import dev.simoneaa.demop5.year.YearEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private int length;

    public MovieEntity() {
    }

    public MovieEntity(Long id, String title, String director, int length) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.length = length;
    }

    @ManyToMany
    @JoinTable(
        name = "movies_genres",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genres = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "year_id")
    private YearEntity year;

    @ManyToMany
    @JoinTable(
        name = "movies_actors",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<ActorEntity> actors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
    }

    public YearEntity getYear() {
        return year;
    }

    public void setYear(YearEntity year) {
        this.year = year;
    }

    public Set<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(Set<ActorEntity> actors) {
        this.actors = actors;
    }

}
