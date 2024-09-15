package Excalibur.BidSystem.Model;

import Excalibur.BidSystem.Model.Event.BidEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Bid extends AbstractAggregateRoot<Bid>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidId;
    private Double bid;
    private Double buyOutBid;
    private Double startBid;
    private Integer bidCounts;
    private Double highestBid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastBidTime;
    private Date bidDate;
    @ManyToOne
    @JsonIgnore  // Prevent circular reference
    private Auction auction;  // Auction the bid is associated with

    @ManyToOne
    private Buyer buyer;

    @Column
    @ElementCollection(fetch = FetchType.EAGER, targetClass=Long.class)
    private List<Long> auctions = new ArrayList<>();

    // Constructors
    public Bid() {}

    public Bid(Double bid, Double buyOutBid, Double startBid, Integer bidCounts, Double highestBid) {
        this.bid = bid;
        this.buyOutBid = buyOutBid;
        this.startBid = startBid;
        this.bidCounts = bidCounts;
        this.highestBid = highestBid;
    }

    // Getters and Setters
    public Long getBidId() { return bidId; }
    public void setBidId(Long bidId) { this.bidId = bidId; }
    public Double getBid() { return bid; }
    public void setBid(Double bid) { this.bid = bid; }
    public Double getBuyOutBid() {
        if (this.highestBid != null) {
            return this.highestBid + 80000;
        }
        return null;
    }
    public void setBuyOutBid(Double buyOutBid) { this.buyOutBid = buyOutBid; }
    public Double getStartBid() { return startBid; }
    public void setStartBid(Double startBid) { this.startBid = startBid; }
    public Integer getBidCounts() { return bidCounts; }
    public void setBidCounts(Integer bidCounts) { this.bidCounts = bidCounts; }
    public Double getHighestBid() { return highestBid; }
    public void setHighestBid(Double highestBid) { this.highestBid = highestBid; }
    public List<Long>getAuctions() {return auctions;}
    public Date getLastBidTime() {return lastBidTime;}
    public void setLastBidTime(Date lastBidTime) {this.lastBidTime = lastBidTime;}

    @Override
    public String toString() {
        return "BidEvent{" +
                ", bid Id='" + bidId + '\'' +
                ", bid='" + bid + '\'' +
                ", buy out bid='" + buyOutBid + '\'' +
                ", start bid='" + startBid + '\'' +
                ", bid counts='" + bidCounts + '\'' +
                ", highest bid='" + highestBid + '\'' +
                '}';
    }

    public boolean checkBids(Long bidId) {
        return this.auctions.contains(bidId);
    }

    public void borrowFrom(Long bidId) {
        if (this.checkBids(bidId)) {
            this.auctions.remove(bidId);
            BidEvent event = new BidEvent();
            event.setEventName("borrow");
            event.setBidEventId(bidId);
            event.setBid(this.getBid());
            event.setBuyOutBid(this.getBuyOutBid());
            event.setStartBid(this.getStartBid());
            event.setBidCounts(this.getBidCounts());
            event.setHighestBid(this.getHighestBid());
            /**
             * Method to register the event
             * @param event
             **/
            registerEvent(event);
        }
    }

    public void returnTo(Long bidId) {
        this.auctions.add(bidId);
    }

}
