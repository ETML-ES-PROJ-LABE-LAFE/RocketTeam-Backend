package ch.etmles.auction.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Auction {
    private @Id @GeneratedValue Long id;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    @ManyToOne
    private Lot lot;

    @ManyToOne
    private Customer customer;

    public Auction() {}

    public Auction(BigDecimal amount, LocalDateTime timestamp, Lot lot, Customer customer) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.lot = lot;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    //can't delete it. if deleted, create bug in the frontend
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
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
        if (!(o instanceof Auction)) return false;
        Auction auction = (Auction) o;
        return Objects.equals(id, auction.id) &&
                Objects.equals(amount, auction.amount) &&
                Objects.equals(timestamp, auction.timestamp) &&
                Objects.equals(lot, auction.lot) &&
                Objects.equals(customer, auction.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, timestamp, lot, customer);
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", lot=" + lot +
                ", customer=" + customer +
                '}';
    }
}
