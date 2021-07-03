package com.profesor.gestiondeStarwars.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicles_people", schema = "public", catalog = "starwars")
@IdClass(VehiclesPeopleEntityPK.class)
public class VehiclesPeopleEntity {
    private int codigoVehicle;
    private int codigoPeople;

    @Id
    @Column(name = "codigo_vehicle", nullable = false)
    public int getCodigoVehicle() {
        return codigoVehicle;
    }

    public void setCodigoVehicle(int codigoVehicle) {
        this.codigoVehicle = codigoVehicle;
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
        VehiclesPeopleEntity that = (VehiclesPeopleEntity) o;
        return codigoVehicle == that.codigoVehicle &&
                codigoPeople == that.codigoPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoVehicle, codigoPeople);
    }
}
