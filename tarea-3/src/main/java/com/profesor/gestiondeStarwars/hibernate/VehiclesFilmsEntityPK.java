package com.profesor.gestiondeStarwars.hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class VehiclesFilmsEntityPK implements Serializable {
    private int codigoVehicle;
    private int codigoFilm;

    @Column(name = "codigo_vehicle", nullable = false)
    @Id
    public int getCodigoVehicle() {
        return codigoVehicle;
    }

    public void setCodigoVehicle(int codigoVehicle) {
        this.codigoVehicle = codigoVehicle;
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
        VehiclesFilmsEntityPK that = (VehiclesFilmsEntityPK) o;
        return codigoVehicle == that.codigoVehicle &&
                codigoFilm == that.codigoFilm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoVehicle, codigoFilm);
    }
}
