package edu.learn.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A UserMP.
 */
@Entity
@Table(name = "user_mp")
public class UserMP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<Bid> bids = new HashSet<>();

    @ManyToOne
    private Item items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public UserMP fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public UserMP billingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getLogin() {
        return login;
    }

    public UserMP login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public UserMP password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public UserMP bids(Set<Bid> bids) {
        this.bids = bids;
        return this;
    }

    public UserMP addBid(Bid bid) {
        bids.add(bid);
        bid.getUsers().add(this);
        return this;
    }

    public UserMP removeBid(Bid bid) {
        bids.remove(bid);
        bid.getUsers().remove(this);
        return this;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Item getItems() {
        return items;
    }

    public UserMP items(Item item) {
        this.items = item;
        return this;
    }

    public void setItems(Item item) {
        this.items = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserMP userMP = (UserMP) o;
        if (userMP.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userMP.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserMP{" +
                "id=" + id +
                ", fullName='" + fullName + "'" +
                ", billingAddress='" + billingAddress + "'" +
                ", login='" + login + "'" +
                ", password='" + password + "'" +
                '}';
    }
}

