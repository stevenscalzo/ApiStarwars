package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "films", schema = "public", catalog = "starwars")
public class FilmsEntity {
    private String title;
    private String episodeId;
    private String openingCrawl;
    private String director;
    private String producer;
    private String releaseDate;
    private int codigo;
    private String created;
    private String edited;

    @Basic
    @Column(name = "title", nullable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "episode_id", nullable = true, length = 100)
    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    @Basic
    @Column(name = "opening_crawl", nullable = true, length = 10485760)
    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    @Basic
    @Column(name = "director", nullable = true, length = 100)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "producer", nullable = true, length = 100)
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Basic
    @Column(name = "release_date", nullable = true, length = 100)
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Id
    @Column(name = "codigo", nullable = false)
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "created", nullable = true, length = 100)
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Basic
    @Column(name = "edited", nullable = true, length = 100)
    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmsEntity that = (FilmsEntity) o;
        return codigo == that.codigo &&
                Objects.equals(title, that.title) &&
                Objects.equals(episodeId, that.episodeId) &&
                Objects.equals(openingCrawl, that.openingCrawl) &&
                Objects.equals(director, that.director) &&
                Objects.equals(producer, that.producer) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(created, that.created) &&
                Objects.equals(edited, that.edited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, episodeId, openingCrawl, director, producer, releaseDate, codigo, created, edited);
    }
}
