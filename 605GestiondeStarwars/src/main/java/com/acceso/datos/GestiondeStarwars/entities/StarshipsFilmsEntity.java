package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "starships_films", schema = "public", catalog = "starwars")
@IdClass(StarshipsFilmsEntityPK.class)
public class StarshipsFilmsEntity {
    private int codigoStarship;
    private int codigoFilm;

    @Id
    @Column(name = "codigo_starship", nullable = false)
    public int getCodigoStarship() {
        return codigoStarship;
    }

    public void setCodigoStarship(int codigoStarship) {
        this.codigoStarship = codigoStarship;
    }

    @Id
    @Column(name = "codigo_film", nullable = false)
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
        StarshipsFilmsEntity that = (StarshipsFilmsEntity) o;
        return codigoStarship == that.codigoStarship &&
                codigoFilm == that.codigoFilm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoStarship, codigoFilm);
    }
}
