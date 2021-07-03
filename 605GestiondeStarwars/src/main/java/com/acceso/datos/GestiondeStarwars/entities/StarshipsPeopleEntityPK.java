package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StarshipsPeopleEntityPK implements Serializable {
    private int codigoStarship;
    private int codigoPeople;

    @Column(name = "codigo_starship", nullable = false)
    @Id
    public int getCodigoStarship() {
        return codigoStarship;
    }

    public void setCodigoStarship(int codigoStarship) {
        this.codigoStarship = codigoStarship;
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
        StarshipsPeopleEntityPK that = (StarshipsPeopleEntityPK) o;
        return codigoStarship == that.codigoStarship &&
                codigoPeople == that.codigoPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoStarship, codigoPeople);
    }
}
