package com.profesor.gestiondeStarwars.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "films_people", schema = "public", catalog = "starwars")
@IdClass(FilmsPeopleEntityPK.class)
public class FilmsPeopleEntity {
    private int codigoFilm;
    private int codigoPeople;

    @Id
    @Column(name = "codigo_film", nullable = false)
    public int getCodigoFilm() {
        return codigoFilm;
    }

    public void setCodigoFilm(int codigoFilm) {
        this.codigoFilm = codigoFilm;
    }

    @Id
    @Column(name = "codigo_people", nullable = false)
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
        FilmsPeopleEntity that = (FilmsPeopleEntity) o;
        return codigoFilm == that.codigoFilm &&
                codigoPeople == that.codigoPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoFilm, codigoPeople);
    }
}
