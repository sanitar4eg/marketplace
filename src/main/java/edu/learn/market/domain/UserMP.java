package edu.learn.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name = "email")
    @Email
    @NotEmpty
    private String email;

    @Column(name = "password")
    @NotNull
    @Length(min = 6)
    private String password;

    @OneToMany(mappedBy = "userMP")
    @JsonIgnore
    private Set<Bid> bids = new HashSet<>();

    @OneToMany(mappedBy = "userMP")
    @JsonIgnore
    private Set<Item> items = new HashSet<>();

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

    public String getEmail() {
        return email;
    }

    public UserMP email(String login) {
        this.email = login;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public UserMP addBids(Bid bid) {
        bids.add(bid);
        bid.setUserMP(this);
        return this;
    }

    public UserMP removeBids(Bid bid) {
        bids.remove(bid);
        bid.setUserMP(null);
        return this;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Set<Item> getItems() {
        return items;
    }

    public UserMP items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public UserMP addItems(Item item) {
        items.add(item);
        item.setUserMP(this);
        return this;
    }

    public UserMP removeItems(Item item) {
        items.remove(item);
        item.setUserMP(null);
        return this;
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
                ", email='" + email + "'" +
                ", password='" + password + "'" +
                '}';
    }
}

