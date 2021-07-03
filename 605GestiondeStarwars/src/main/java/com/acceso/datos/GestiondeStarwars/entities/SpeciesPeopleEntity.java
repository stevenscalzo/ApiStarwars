package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "species_people", schema = "public", catalog = "starwars")
@IdClass(SpeciesPeopleEntityPK.class)
public class SpeciesPeopleEntity {
    private int codigoSpecie;
    private int codigoPeople;

    @Id
    @Column(name = "codigo_specie", nullable = false)
    public int getCodigoSpecie() {
        return codigoSpecie;
    }

    public void setCodigoSpecie(int codigoSpecie) {
        this.codigoSpecie = codigoSpecie;
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
        SpeciesPeopleEntity that = (SpeciesPeopleEntity) o;
        return codigoSpecie == that.codigoSpecie &&
                codigoPeople == that.codigoPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoSpecie, codigoPeople);
    }
}
