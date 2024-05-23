package ch.etmles.auction.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Enchere {
    private @Id @GeneratedValue Long id;
    private double amount;
    private LocalDateTime timestamp;

    @ManyToOne
    private Lot lot;

    public Enchere() {}

    public Enchere(double amount, LocalDateTime timestamp, Lot lot) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.lot = lot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enchere)) return false;
        Enchere enchere = (Enchere) o;
        return Double.compare(enchere.amount, amount) == 0 && Objects.equals(id, enchere.id) && Objects.equals(timestamp, enchere.timestamp) && Objects.equals(lot, enchere.lot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, timestamp, lot);
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "id=" + id +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", lot=" + lot +
                '}';
    }
}
