package app.domain;

import java.util.Objects;

public class Pet {

    private int id;
    private String name;
    private String breed;
    private String color;
    private int age;
    private boolean active;

    public Pet() {
    }

    public Pet(int id, String name, String breed, String color, int age, boolean active) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.age = age;
        this.active = active;
    }

    public Pet(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public Pet(String name, String breed, String color, int age) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        Pet pet = (Pet) o;
        return id == pet.id && age == pet.age && active == pet.active && Objects.equals(name, pet.name) && Objects.equals(breed, pet.breed) && Objects.equals(color, pet.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, color, age, active);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", active=" + active +
                '}';
    }
}
