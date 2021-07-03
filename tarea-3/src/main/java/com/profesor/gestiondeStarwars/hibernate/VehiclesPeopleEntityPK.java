package com.profesor.gestiondeStarwars.hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class VehiclesPeopleEntityPK implements Serializable {
    private int codigoVehicle;
    private int codigoPeople;

    @Column(name = "codigo_vehicle", nullable = false)
    @Id
    public int getCodigoVehicle() {
        return codigoVehicle;
    }

    public void setCodigoVehicle(int codigoVehicle) {
        this.codigoVehicle = codigoVehicle;
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
        VehiclesPeopleEntityPK that = (VehiclesPeopleEntityPK) o;
        return codigoVehicle == that.codigoVehicle &&
                codigoPeople == that.codigoPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoVehicle, codigoPeople);
    }
}
