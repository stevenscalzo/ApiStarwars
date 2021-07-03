package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "starships_people", schema = "public", catalog = "starwars")
@IdClass(StarshipsPeopleEntityPK.class)
public class StarshipsPeopleEntity {
    private int codigoStarship;
    private int codigoPeople;

    @Id
    @Column(name = "codigo_starship", nullable = false)
    public int getCodigoStarship() {
        return codigoStarship;
    }

    public void setCodigoStarship(int codigoStarship) {
        this.codigoStarship = codigoStarship;
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
        StarshipsPeopleEntity that = (StarshipsPeopleEntity) o;
        return codigoStarship == that.codigoStarship &&
                codigoPeople == that.codigoPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoStarship, codigoPeople);
    }
}
