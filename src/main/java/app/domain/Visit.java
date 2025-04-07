package app.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Visit {

    private int id;
    private Owner owner;
    private Pet pet;
    private Vet vet;
    private LocalDateTime date;
    private String description;
    private boolean active;
    //private boolean sick;


    public Visit() {
    }

    public Visit(int id, Owner owner, Pet pet, Vet vet, LocalDateTime date, String description, boolean active) {
        this.id = id;
        this.owner = owner;
        this.pet = pet;
        this.vet = vet;
        this.date = date;
        this.description = description;
        this.active = active;
    }

    public Visit(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return id == visit.id && active == visit.active && Objects.equals(owner, visit.owner) && Objects.equals(pet, visit.pet) && Objects.equals(vet, visit.vet) && Objects.equals(date, visit.date) && Objects.equals(description, visit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, pet, vet, date, description, active);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", owner=" + owner +
                ", pet=" + pet +
                ", vet=" + vet +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
