package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SpeciesPeopleEntityPK implements Serializable {
    private int codigoSpecie;
    private int codigoPeople;

    @Column(name = "codigo_specie", nullable = false)
    @Id
    public int getCodigoSpecie() {
        return codigoSpecie;
    }

    public void setCodigoSpecie(int codigoSpecie) {
        this.codigoSpecie = codigoSpecie;
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
        SpeciesPeopleEntityPK that = (SpeciesPeopleEntityPK) o;
        return codigoSpecie == that.codigoSpecie &&
                codigoPeople == that.codigoPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoSpecie, codigoPeople);
    }
}
