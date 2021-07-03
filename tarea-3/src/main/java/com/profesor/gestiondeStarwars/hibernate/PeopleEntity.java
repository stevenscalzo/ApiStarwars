package com.profesor.gestiondeStarwars.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "people", schema = "public", catalog = "starwars")
public class PeopleEntity {
    private String name;
    private String birthYear;
    private String eyeColor;
    private String gender;
    private String hairColor;
    private String height;
    private String mass;
    private String skinColor;
    private Integer homeworld;
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
    @Column(name = "birth_year", nullable = true, length = 100)
    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    @Basic
    @Column(name = "eye_color", nullable = true, length = 100)
    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = 100)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "hair_color", nullable = true, length = 100)
    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    @Basic
    @Column(name = "height", nullable = true, length = 100)
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Basic
    @Column(name = "mass", nullable = true, length = 100)
    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    @Basic
    @Column(name = "skin_color", nullable = true, length = 100)
    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    @Basic
    @Column(name = "homeworld", nullable = true)
    public Integer getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(Integer homeworld) {
        this.homeworld = homeworld;
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
        PeopleEntity that = (PeopleEntity) o;
        return codigo == that.codigo &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthYear, that.birthYear) &&
                Objects.equals(eyeColor, that.eyeColor) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(hairColor, that.hairColor) &&
                Objects.equals(height, that.height) &&
                Objects.equals(mass, that.mass) &&
                Objects.equals(skinColor, that.skinColor) &&
                Objects.equals(homeworld, that.homeworld) &&
                Objects.equals(created, that.created) &&
                Objects.equals(edited, that.edited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthYear, eyeColor, gender, hairColor, height, mass, skinColor, homeworld, codigo, created, edited);
    }
}
