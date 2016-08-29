package edu.learn.market.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Bid.
 */
@Entity
@Table(name = "bids")
public class Bid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bid")
    private Long bid;

    @ManyToMany
    @NotNull
    @JoinTable(name = "bid_users",
            joinColumns = @JoinColumn(name = "bids_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "ID"))
    private Set<UserMP> users = new HashSet<>();

    @ManyToMany
    @NotNull
    @JoinTable(name = "bid_items",
            joinColumns = @JoinColumn(name = "bids_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "items_id", referencedColumnName = "ID"))
    private Set<Item> items = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBid() {
        return bid;
    }

    public Bid bid(Long bid) {
        this.bid = bid;
        return this;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Set<UserMP> getUsers() {
        return users;
    }

    public Bid users(Set<UserMP> UserMPS) {
        this.users = UserMPS;
        return this;
    }

    public Bid addUserMP(UserMP UserMP) {
        users.add(UserMP);
        UserMP.getBids().add(this);
        return this;
    }

    public Bid removeUserMP(UserMP UserMP) {
        users.remove(UserMP);
        UserMP.getBids().remove(this);
        return this;
    }

    public void setUsers(Set<UserMP> UserMPS) {
        this.users = UserMPS;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Bid items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Bid addItem(Item item) {
        items.add(item);
        item.getBids().add(this);
        return this;
    }

    public Bid removeItem(Item item) {
        items.remove(item);
        item.getBids().remove(this);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bid bid = (Bid) o;
        if (bid.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, bid.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", bid='" + bid + "'" +
                '}';
    }
}

