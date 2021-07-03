package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FilmsPeopleEntityPK implements Serializable {
    private int codigoFilm;
    private int codigoPeople;

    @Column(name = "codigo_film", nullable = false)
    @Id
    public int getCodigoFilm() {
        return codigoFilm;
    }

    public void setCodigoFilm(int codigoFilm) {
        this.codigoFilm = codigoFilm;
    }

    @Column(name = "codigo_people", nullable = false)
    @Id
    public int getCodigoPeople() {
        return codigoPeople;
    }

    public void setCodigoPeople(int codigoPeople) {
        this.codigoPeople = codigoPeople;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmsPeopleEntityPK that = (FilmsPeopleEntityPK) o;
        return codigoFilm == that.codigoFilm &&
                codigoPeople == that.codigoPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoFilm, codigoPeople);
    }
}
