package ch.etmles.auction.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Notification {

    private @Id @GeneratedValue Long id;
    private String message;
    private boolean read = false;
    private LocalDateTime timestamp;
    private BigDecimal bidAmount;

    @ManyToOne
    private Customer user;

    @ManyToOne
    private Lot lot;

    public Notification() {}

    public Notification(String message, Customer user, Lot lot, BigDecimal bidAmount) {
        this.message = message;
        this.user = user;
        this.lot = lot;
        this.bidAmount = bidAmount;
        this.timestamp = LocalDateTime.now();
        this.read = false;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return id.equals(that.id) &&
                Objects.equals(message, that.message) &&
                Objects.equals(user, that.user) &&
                Objects.equals(lot, that.lot) &&
                Objects.equals(bidAmount, that.bidAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, user, lot, bidAmount);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", read=" + read +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", lot=" + lot +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
