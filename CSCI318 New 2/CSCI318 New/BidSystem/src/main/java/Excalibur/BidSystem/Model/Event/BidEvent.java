package Excalibur.BidSystem.Model.Event;

import jakarta.persistence.*;

@Entity
public class BidEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidEventId;

    @Column
    Long bidId;

    @Column
    private String eventName;

    @Column
    private Double bid;

    @Column
    private Double buyOutBid;

    @Column
    private Double startBid;

    @Column
    private Integer bidCounts;

    @Column
    private Double highestBid;

    @Column
    Long auctionId;

    public BidEvent() {
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public Long getBidEventId() { return bidEventId; }
    public void setBidEventId(Long bidId) { this.bidEventId = bidId; }
    public Long getBidId(){return this.bidId;}
    public void setBidId(Long bidId) {this.bidId = bidId;}
    public Double getBid() { return bid; }
    public void setBid(Double bid) { this.bid = bid; }
    public Double getBuyOutBid() { return buyOutBid; }
    public void setBuyOutBid(Double buyOutBid) { this.buyOutBid = buyOutBid; }
    public Double getStartBid() { return startBid; }
    public void setStartBid(Double startBid) { this.startBid = startBid; }
    public Integer getBidCounts() { return bidCounts; }
    public void setBidCounts(Integer bidCounts) { this.bidCounts = bidCounts; }
    public Double getHighestBid() { return highestBid; }
    public void setHighestBid(Double highestBid) { this.highestBid = highestBid; }
    public void setAuctionId(Long auctionId) {this.auctionId = auctionId;}

    @Override
    public String toString() {
        return "BidEvent{" +
                "event_name='" + eventName + '\'' +
                ", bidId='" + bidId + '\'' +
                ", bid='" + bid + '\'' +
                ", buyOutBid='" + buyOutBid + '\'' +
                ", startBid='" + startBid + '\'' +
                ", bidCounts='" + bidCounts + '\'' +
                ", highestBid='" + highestBid + '\'' +
                ", auctionId='" + auctionId + '\'' +
                "}'";
    }
}
