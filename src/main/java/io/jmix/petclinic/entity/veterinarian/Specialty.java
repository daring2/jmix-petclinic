package io.jmix.petclinic.entity.veterinarian;

import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.petclinic.entity.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@JmixEntity
@Table(name = "PETCLINIC_SPECIALTY")
@Entity(name = "petclinic_Specialty")
public class Specialty extends NamedEntity {

    @JmixProperty
    @Transient
    private Specialty parent;

    public Specialty getParent() {
        return parent;
    }

    public void setParent(Specialty parent) {
        this.parent = parent;
    }

}