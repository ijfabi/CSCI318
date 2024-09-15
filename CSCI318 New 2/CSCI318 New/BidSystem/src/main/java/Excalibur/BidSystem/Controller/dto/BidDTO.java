package Excalibur.BidSystem.Controller.dto;

import java.util.Date;

public class BidDTO {
    private Long bidId;
    private Double bid;
    private Double buyOutBid;
    private Double startBid;
    private Integer bidCounts;
    private Double highestBid;
    private Date lastBidTime;

    public Long getBidId() { return bidId; }
    public void setBidId(Long bidId) { this.bidId = bidId; }
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
    public Date getLastBidTime() {return lastBidTime;}
    public void setLastBidTime(Date lastBidTime) {this.lastBidTime = lastBidTime;}
}
