package com.acceso.datos.GestiondeStarwars.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "planets", schema = "public", catalog = "starwars")
public class PlanetsEntity {
    private String name;
    private String diameter;
    private String rotationPeriod;
    private String orbitalPeriod;
    private String gravity;
    private String population;
    private String climate;
    private String terrain;
    private String surfaceWater;
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
    @Column(name = "diameter", nullable = true, length = 100)
    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    @Basic
    @Column(name = "rotation_period", nullable = true, length = 100)
    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(String rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    @Basic
    @Column(name = "orbital_period", nullable = true, length = 100)
    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(String orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    @Basic
    @Column(name = "gravity", nullable = true, length = 100)
    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    @Basic
    @Column(name = "population", nullable = true, length = 100)
    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Basic
    @Column(name = "climate", nullable = true, length = 100)
    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    @Basic
    @Column(name = "terrain", nullable = true, length = 100)
    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Basic
    @Column(name = "surface_water", nullable = true, length = 100)
    public String getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(String surfaceWater) {
        this.surfaceWater = surfaceWater;
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
        PlanetsEntity that = (PlanetsEntity) o;
        return codigo == that.codigo &&
                Objects.equals(name, that.name) &&
                Objects.equals(diameter, that.diameter) &&
                Objects.equals(rotationPeriod, that.rotationPeriod) &&
                Objects.equals(orbitalPeriod, that.orbitalPeriod) &&
                Objects.equals(gravity, that.gravity) &&
                Objects.equals(population, that.population) &&
                Objects.equals(climate, that.climate) &&
                Objects.equals(terrain, that.terrain) &&
                Objects.equals(surfaceWater, that.surfaceWater) &&
                Objects.equals(created, that.created) &&
                Objects.equals(edited, that.edited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, diameter, rotationPeriod, orbitalPeriod, gravity, population, climate, terrain, surfaceWater, codigo, created, edited);
    }
}
