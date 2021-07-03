package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FilmsPlanetsEntityPK implements Serializable {
    private int codigoFilm;
    private int codigoPlanet;

    @Column(name = "codigo_film", nullable = false)
    @Id
    public int getCodigoFilm() {
        return codigoFilm;
    }

    public void setCodigoFilm(int codigoFilm) {
        this.codigoFilm = codigoFilm;
    }

    @Column(name = "codigo_planet", nullable = false)
    @Id
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
        FilmsPlanetsEntityPK that = (FilmsPlanetsEntityPK) o;
        return codigoFilm == that.codigoFilm &&
                codigoPlanet == that.codigoPlanet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoFilm, codigoPlanet);
    }
}
