package com.profesor.gestiondeStarwars.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicles_films", schema = "public", catalog = "starwars")
@IdClass(VehiclesFilmsEntityPK.class)
public class VehiclesFilmsEntity {
    private int codigoVehicle;
    private int codigoFilm;

    @Id
    @Column(name = "codigo_vehicle", nullable = false)
    public int getCodigoVehicle() {
        return codigoVehicle;
    }

    public void setCodigoVehicle(int codigoVehicle) {
        this.codigoVehicle = codigoVehicle;
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
        VehiclesFilmsEntity that = (VehiclesFilmsEntity) o;
        return codigoVehicle == that.codigoVehicle &&
                codigoFilm == that.codigoFilm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoVehicle, codigoFilm);
    }
}
