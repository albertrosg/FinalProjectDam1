/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entitys;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Albert
 */
@Entity
@Table(name="pet")
public class Pet implements Serializable{
    
    @Id
    @Column(name="chipNumber", unique = true)
    private String chipNumber;
    
    @Column(name="name")
    private String name;
    
    @Column(name="type")
    private String type;
    
    @Column(name="race")
    private String race;    
    
    @Column(name="weight")
    private double weight;
    
    @Column(name="birthDate")
    private LocalDate birthDate;
    
    @Column(name="sex")
    private String sex;
    
    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="ownerId")
    private Owner owner;
    
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Visit> visit;
    

    public Pet() {
    }

    public Pet(String chipNumber, String name, String type) {
        this.chipNumber = chipNumber;
        this.name = name;
        this.type = type;
    }
    
    

    public Pet(String chipNumber, String name, String type, String race, String sex ,double weight, LocalDate birthDate, Owner owner) {
        this.chipNumber = chipNumber;
        this.name = name;
        this.type = type;
        this.race = race;
        this.sex = sex;
        this.weight = weight;
        this.birthDate = birthDate;
        this.owner = owner;
    }

    public Pet(String chipNumber, String name, String type, String race, double weight, LocalDate birthDate, String sex, Owner owner, List<Visit> visit) {
        this.chipNumber = chipNumber;
        this.name = name;
        this.type = type;
        this.race = race;
        this.weight = weight;
        this.birthDate = birthDate;
        this.sex = sex;
        this.owner = owner;
        this.visit = visit;
    }
    
    

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Visit> getVisit() {
        return visit;
    }

    public void setVisit(List<Visit> visit) {
        this.visit = visit;
    }
    
    
    
}
