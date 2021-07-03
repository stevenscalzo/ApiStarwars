package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicles", schema = "public", catalog = "starwars")
public class VehiclesEntity {
    private String name;
    private String model;
    private String vehicleClass;
    private String manufacturer;
    private String length;
    private String costInCredits;
    private String crew;
    private String passengers;
    private String maxAtmospheringSpeed;
    private String cargoCapacity;
    private String consumables;
    private int codigo;
    private String created;
    private String edited;

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "model", nullable = true, length = 100)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "vehicle_class", nullable = true, length = 100)
    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    @Basic
    @Column(name = "manufacturer", nullable = true, length = 100)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Basic
    @Column(name = "length", nullable = true, length = 100)
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Basic
    @Column(name = "cost_in_credits", nullable = true, length = 100)
    public String getCostInCredits() {
        return costInCredits;
    }

    public void setCostInCredits(String costInCredits) {
        this.costInCredits = costInCredits;
    }

    @Basic
    @Column(name = "crew", nullable = true, length = 100)
    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    @Basic
    @Column(name = "passengers", nullable = true, length = 100)
    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    @Basic
    @Column(name = "max_atmosphering_speed", nullable = true, length = 100)
    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
    }

    @Basic
    @Column(name = "cargo_capacity", nullable = true, length = 100)
    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(String cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Basic
    @Column(name = "consumables", nullable = true, length = 100)
    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    @Id
    @Column(name = "codigo", nullable = false)
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "created", nullable = true, length = 100)
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Basic
    @Column(name = "edited", nullable = true, length = 100)
    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiclesEntity that = (VehiclesEntity) o;
        return codigo == that.codigo &&
                Objects.equals(name, that.name) &&
                Objects.equals(model, that.model) &&
                Objects.equals(vehicleClass, that.vehicleClass) &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(length, that.length) &&
                Objects.equals(costInCredits, that.costInCredits) &&
                Objects.equals(crew, that.crew) &&
                Objects.equals(passengers, that.passengers) &&
                Objects.equals(maxAtmospheringSpeed, that.maxAtmospheringSpeed) &&
                Objects.equals(cargoCapacity, that.cargoCapacity) &&
                Objects.equals(consumables, that.consumables) &&
                Objects.equals(created, that.created) &&
                Objects.equals(edited, that.edited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, vehicleClass, manufacturer, length, costInCredits, crew, passengers, maxAtmospheringSpeed, cargoCapacity, consumables, codigo, created, edited);
    }
}
