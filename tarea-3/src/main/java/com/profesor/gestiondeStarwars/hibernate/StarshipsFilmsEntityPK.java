package com.profesor.gestiondeStarwars.hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StarshipsFilmsEntityPK implements Serializable {
    private int codigoStarship;
    private int codigoFilm;

    @Column(name = "codigo_starship", nullable = false)
    @Id
    public int getCodigoStarship() {
        return codigoStarship;
    }

    public void setCodigoStarship(int codigoStarship) {
        this.codigoStarship = codigoStarship;
    }

    @Column(name = "codigo_film", nullable = false)
    @Id
    public int getCodigoFilm() {
        return codigoFilm;
    }

    public void setCodigoFilm(int codigoFilm) {
        this.codigoFilm = codigoFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarshipsFilmsEntityPK that = (StarshipsFilmsEntityPK) o;
        return codigoStarship == that.codigoStarship &&
                codigoFilm == that.codigoFilm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoStarship, codigoFilm);
    }
}
