package ch.etmles.auction.Entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Lot {

    private @Id @GeneratedValue Long id;

    private String description;

    @ManyToOne
    private Category category;

    private double initialPrice;
    private double highestBid;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Lot() {}

    public Lot(String description, Category category, double initialPrice, double highestBid, Customer customer) {
        this.description = description;
        this.category = category;
        this.initialPrice = initialPrice;
        this.highestBid = highestBid;
        this.customer = customer;
    }

    // Getter and Setter for user
    public Customer getUser() {
        return customer;
    }

    public void setUser(Customer user) {
        this.customer = user;
    }

    //can't delete it. if deleted, create bug in the frontend
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
                ", category='" + category + '\'' +
                ", initialPrice=" + initialPrice +
                ", highestBid=" + highestBid +
                '}';
    }
}
