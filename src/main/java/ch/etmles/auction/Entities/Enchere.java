package ch.etmles.auction.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Enchere {
    private @Id @GeneratedValue Long id;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    @ManyToOne
    private Lot lot;

    @ManyToOne
    private Customer customer;

    public Enchere() {}

    public Enchere(BigDecimal amount, LocalDateTime timestamp, Lot lot, Customer customer) {
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
        if (!(o instanceof Enchere)) return false;
        Enchere enchere = (Enchere) o;
        return Objects.equals(id, enchere.id) &&
                Objects.equals(amount, enchere.amount) &&
                Objects.equals(timestamp, enchere.timestamp) &&
                Objects.equals(lot, enchere.lot) &&
                Objects.equals(customer, enchere.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, timestamp, lot, customer);
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "id=" + id +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", lot=" + lot +
                ", customer=" + customer +
                '}';
    }
}
