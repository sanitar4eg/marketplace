package edu.learn.market.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

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

    @ManyToOne
    @NotNull
    private UserMP userMP;

    @ManyToOne
    @NotNull
    private Item item;

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

    public UserMP getUserMP() {
        return userMP;
    }

    public Bid userMP(UserMP UserMP) {
        this.userMP = UserMP;
        return this;
    }

    public void setUserMP(UserMP UserMP) {
        this.userMP = UserMP;
    }

    public Item getItem() {
        return item;
    }

    public Bid item(Item item) {
        this.item = item;
        return this;
    }

    public void setItem(Item item) {
        this.item = item;
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

