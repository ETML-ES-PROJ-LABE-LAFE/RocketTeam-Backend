package ch.etmles.auction.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Entity
// Cette annotation est utilisée pour gérer les références circulaires lors de la sérialisation JSON.
// Elle indique à Jackson d'utiliser l'identifiant (ici, le champ "id") pour gérer les références.
// Lorsque Jackson sérialise cet objet, il utilise la valeur du champ "id" pour représenter l'objet.
// Cela permet d'éviter les boucles infinies lors de la sérialisation/desérialisation, en particulier pour les relations bidirectionnelles.
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Lot {

    private @Id @GeneratedValue Long id;

    private String description;

    @ManyToOne
    private Category category;

    private double initialPrice;
    private double highestBid;
    private boolean active = true;

    @ManyToOne
    private Customer customer;

    public Lot() {}

    public Lot(String description, Category category, double initialPrice, double highestBid, boolean active, Customer customer) {
        this.description = description;
        this.category = category;
        this.initialPrice = initialPrice;
        this.highestBid = highestBid;
        this.active = active;
        this.customer = customer;
    }

    // getters and setters

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
                ", category='" + category + '\'' +
                ", initialPrice=" + initialPrice +
                ", highestBid=" + highestBid +
                '}';
    }
}
