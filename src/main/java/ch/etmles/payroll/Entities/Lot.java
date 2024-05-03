package ch.etmles.payroll.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Lot {
    private @Id
    @GeneratedValue Long id;
    private String description;
    @ManyToOne
    private Category category;
    private double initialPrice;
    private double highestBid;

    public Lot() {}

    public Lot(String description, Category category, double initialPrice, double highestBid) {
        this.description = description;
        this.category = category;
        this.initialPrice = initialPrice;
        this.highestBid = highestBid;
    }

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
        if (!(o instanceof Lot lot)) return false;
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
