package edu.learn.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Item.
 */
@Entity
@Table(name = "items")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_price")
    private Long startPrice;

    @Column(name = "time_left")
    private Long timeLeft;

    @Column(name = "start_bidding_date")
    private LocalDate startBiddingDate;

    @Column(name = "buy_it_now")
    private Boolean buyItNow;

    @NotNull
    @Column(name = "bid_increment", nullable = false)
    private Long bidIncrement;

    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private Set<Bid> bids = new HashSet<>();

    @OneToMany(mappedBy = "items")
    @JsonIgnore
    private Set<UserMP> userMPS = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Item title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Item description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public Item startPrice(Long startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
    }

    public Long getTimeLeft() {
        return timeLeft;
    }

    public Item timeLeft(Long timeLeft) {
        this.timeLeft = timeLeft;
        return this;
    }

    public void setTimeLeft(Long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public LocalDate getStartBiddingDate() {
        return startBiddingDate;
    }

    public Item startBiddingDate(LocalDate startBiddingDate) {
        this.startBiddingDate = startBiddingDate;
        return this;
    }

    public void setStartBiddingDate(LocalDate startBiddingDate) {
        this.startBiddingDate = startBiddingDate;
    }

    public Boolean isBuyItNow() {
        return buyItNow;
    }

    public Item buyItNow(Boolean buyItNow) {
        this.buyItNow = buyItNow;
        return this;
    }

    public void setBuyItNow(Boolean buyItNow) {
        this.buyItNow = buyItNow;
    }

    public Long getBidIncrement() {
        return bidIncrement;
    }

    public Item bidIncrement(Long bidIncrement) {
        this.bidIncrement = bidIncrement;
        return this;
    }

    public void setBidIncrement(Long bidIncrement) {
        this.bidIncrement = bidIncrement;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public Item bids(Set<Bid> bids) {
        this.bids = bids;
        return this;
    }

    public Item addBid(Bid bid) {
        bids.add(bid);
        bid.getItems().add(this);
        return this;
    }

    public Item removeBid(Bid bid) {
        bids.remove(bid);
        bid.getItems().remove(this);
        return this;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Set<UserMP> getUserMPS() {
        return userMPS;
    }

    public Item userMPS(Set<UserMP> UserMPS) {
        this.userMPS = UserMPS;
        return this;
    }

    public Item addUserMP(UserMP UserMP) {
        userMPS.add(UserMP);
        UserMP.setItems(this);
        return this;
    }

    public Item removeUserMP(UserMP UserMP) {
        userMPS.remove(UserMP);
        UserMP.setItems(null);
        return this;
    }

    public void setUserMPS(Set<UserMP> UserMPS) {
        this.userMPS = UserMPS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        if (item.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", description='" + description + "'" +
                ", startPrice='" + startPrice + "'" +
                ", timeLeft='" + timeLeft + "'" +
                ", startBiddingDate='" + startBiddingDate + "'" +
                ", buyItNow='" + buyItNow + "'" +
                ", bidIncrement='" + bidIncrement + "'" +
                '}';
    }
}
