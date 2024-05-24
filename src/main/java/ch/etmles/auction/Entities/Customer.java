package ch.etmles.auction.Entities;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer {


    private @Id @GeneratedValue Long id;

    private String customername;
    private String email;

    @OneToMany(mappedBy = "customer")
    private Set<Lot> lots;

    public Customer() {}

    public Customer(String customername, String email) {
        this.customername = customername;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Lot> getLots() {
        return lots;
    }

    public void setLots(Set<Lot> lots) {
        this.lots = lots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customername='" + customername + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
