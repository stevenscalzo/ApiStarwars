package com.profesor.gestiondeStarwars.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "species", schema = "public", catalog = "starwars")
public class SpeciesEntity {
    private String name;
    private String classification;
    private String designation;
    private String averageHeight;
    private String averageLifespan;
    private String eyeColors;
    private String hairColors;
    private String skinColors;
    private String language;
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
    @Column(name = "classification", nullable = true, length = 100)
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Basic
    @Column(name = "designation", nullable = true, length = 100)
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Basic
    @Column(name = "average_height", nullable = true, length = 100)
    public String getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(String averageHeight) {
        this.averageHeight = averageHeight;
    }

    @Basic
    @Column(name = "average_lifespan", nullable = true, length = 100)
    public String getAverageLifespan() {
        return averageLifespan;
    }

    public void setAverageLifespan(String averageLifespan) {
        this.averageLifespan = averageLifespan;
    }

    @Basic
    @Column(name = "eye_colors", nullable = true, length = 100)
    public String getEyeColors() {
        return eyeColors;
    }

    public void setEyeColors(String eyeColors) {
        this.eyeColors = eyeColors;
    }

    @Basic
    @Column(name = "hair_colors", nullable = true, length = 100)
    public String getHairColors() {
        return hairColors;
    }

    public void setHairColors(String hairColors) {
        this.hairColors = hairColors;
    }

    @Basic
    @Column(name = "skin_colors", nullable = true, length = 100)
    public String getSkinColors() {
        return skinColors;
    }

    public void setSkinColors(String skinColors) {
        this.skinColors = skinColors;
    }

    @Basic
    @Column(name = "language", nullable = true, length = 100)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        SpeciesEntity that = (SpeciesEntity) o;
        return codigo == that.codigo &&
                Objects.equals(name, that.name) &&
                Objects.equals(classification, that.classification) &&
                Objects.equals(designation, that.designation) &&
                Objects.equals(averageHeight, that.averageHeight) &&
                Objects.equals(averageLifespan, that.averageLifespan) &&
                Objects.equals(eyeColors, that.eyeColors) &&
                Objects.equals(hairColors, that.hairColors) &&
                Objects.equals(skinColors, that.skinColors) &&
                Objects.equals(language, that.language) &&
                Objects.equals(homeworld, that.homeworld) &&
                Objects.equals(created, that.created) &&
                Objects.equals(edited, that.edited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, classification, designation, averageHeight, averageLifespan, eyeColors, hairColors, skinColors, language, homeworld, codigo, created, edited);
    }
}
