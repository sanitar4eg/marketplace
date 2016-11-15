package edu.learn.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private Double startPrice;

    @Column(name = "time_left")
    @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME)
    private LocalTime timeLeft;

    @Column(name = "start_bidding_date")
    private LocalDateTime startBiddingDate;

    @Column(name = "buy_it_now")
    private Boolean buyItNow;

    @Column(name = "bid_increment", nullable = false)
    private Double bidIncrement;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private Set<Bid> bids = new HashSet<>();

    @ManyToOne
    private UserMP userMP;

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

    public Double getStartPrice() {
        return startPrice;
    }

    public Item startPrice(Double startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public LocalTime getTimeLeft() {
        return timeLeft;
    }

    public Item timeLeft(LocalTime timeLeft) {
        this.timeLeft = timeLeft;
        return this;
    }

    public void setTimeLeft(LocalTime timeLeft) {
        this.timeLeft = timeLeft;
    }

    public LocalDateTime getStartBiddingDate() {
        return startBiddingDate;
    }

    public Item startBiddingDate(LocalDateTime startBiddingDate) {
        this.startBiddingDate = startBiddingDate;
        return this;
    }

    public void setStartBiddingDate(LocalDateTime startBiddingDate) {
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

    public Double getBidIncrement() {
        return bidIncrement;
    }

    public Item bidIncrement(Double bidIncrement) {
        this.bidIncrement = bidIncrement;
        return this;
    }

    public void setBidIncrement(Double bidIncrement) {
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
        bid.setItem(this);
        return this;
    }

    public Item removeBid(Bid bid) {
        bids.remove(bid);
        bid.setItem(null);
        return this;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public UserMP getUserMP() {
        return userMP;
    }

    public Item userMP(UserMP userMP) {
        this.userMP = userMP;
        return this;
    }

    public void setUserMP(UserMP UserMP) {
        this.userMP = UserMP;
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
