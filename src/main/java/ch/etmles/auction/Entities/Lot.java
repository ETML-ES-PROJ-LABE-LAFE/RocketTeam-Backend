package ch.etmles.auction.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Lot {

    private @Id @GeneratedValue Long id;
    private String description;
    private String image; // Ajout du champ image

    @ManyToOne
    private Category category;

    private double initialPrice;
    private double highestBid;
    private boolean active = true;

    @ManyToOne
    @JsonManagedReference
    private Customer customer;

    public Lot() {}

    public Lot(String description, String image, Category category, double initialPrice, double highestBid, boolean active, Customer customer) {
        this.description = description;
        this.image = image;
        this.category = category;
        this.initialPrice = initialPrice;
        this.highestBid = highestBid;
        this.active = active;
        this.customer = customer;
    }

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lot)) return false;
        Lot lot = (Lot) o;
        return Objects.equals(id, lot.id) &&
                Objects.equals(description, lot.description) &&
                Objects.equals(category, lot.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, category);
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category=" + category +
                ", initialPrice=" + initialPrice +
                ", highestBid=" + highestBid +
                ", active=" + active +
                ", customer=" + customer +
                '}';
    }
}
