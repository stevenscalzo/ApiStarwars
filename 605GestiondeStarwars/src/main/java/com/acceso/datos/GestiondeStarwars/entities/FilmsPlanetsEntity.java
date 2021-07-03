package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "films_planets", schema = "public", catalog = "starwars")
@IdClass(FilmsPlanetsEntityPK.class)
public class FilmsPlanetsEntity {
    private int codigoFilm;
    private int codigoPlanet;

    @Id
    @Column(name = "codigo_film", nullable = false)
    public int getCodigoFilm() {
        return codigoFilm;
    }

    public void setCodigoFilm(int codigoFilm) {
        this.codigoFilm = codigoFilm;
    }

    @Id
    @Column(name = "codigo_planet", nullable = false)
    public int getCodigoPlanet() {
        return codigoPlanet;
    }

    public void setCodigoPlanet(int codigoPlanet) {
        this.codigoPlanet = codigoPlanet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmsPlanetsEntity that = (FilmsPlanetsEntity) o;
        return codigoFilm == that.codigoFilm &&
                codigoPlanet == that.codigoPlanet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoFilm, codigoPlanet);
    }
}
