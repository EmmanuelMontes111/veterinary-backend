package com.programacion.distribuida.paralela.veterinary.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String dose;

    public Medicine() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDose() {
        return this.dose;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Medicine)) return false;
        final Medicine other = (Medicine) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$dose = this.getDose();
        final Object other$dose = other.getDose();
        if (this$dose == null ? other$dose != null : !this$dose.equals(other$dose)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Medicine;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $dose = this.getDose();
        result = result * PRIME + ($dose == null ? 43 : $dose.hashCode());
        return result;
    }

    public String toString() {
        return "Medicine(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", dose=" + this.getDose() + ")";
    }

//    @ManyToMany(mappedBy = "medicines")
//    private List<Pet> pets;
}
