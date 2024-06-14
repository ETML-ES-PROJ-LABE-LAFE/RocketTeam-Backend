package ch.etmles.auction.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.Objects; // Ajout de cette ligne pour importer la classe Objects

@Entity
public class Lot {

    private @Id @GeneratedValue Long id;
    private String description;
    private String image;
    private double initialPrice;
    private BigDecimal highestBid;
    private String status; // New field for status

    @ManyToOne
    private Category category;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Customer highestBidder;

    public Lot() {}

    public Lot(String description, String image, Category category, double initialPrice, double highestBid, String status, Customer customer) {
        this.description = description;
        this.image = image;
        this.category = category;
        this.initialPrice = initialPrice;
        this.highestBid = BigDecimal.valueOf(highestBid);
        this.status = status; // Initialize new field
        this.customer = customer;
    }

    // Getters and Setters

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

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(Customer highestBidder) {
        this.highestBidder = highestBidder;
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
                ", status='" + status + '\'' +
                ", customer=" + customer +
                ", highestBidder=" + highestBidder +
                '}';
    }
}
