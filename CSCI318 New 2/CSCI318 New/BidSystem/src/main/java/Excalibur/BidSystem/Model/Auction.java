package Excalibur.BidSystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Auction extends AbstractAggregateRoot<Auction> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    private Double currentPrice;
    private String status;
    private String realEstateCo;

    // Relationships
    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Bid> bids = new ArrayList<>();

    @Column
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Long.class)
    private List<Long> realEstateAgents = new ArrayList<>();

    // Constructors
    public Auction() {}

    public Auction(String name, Date startDate, Date finishDate, Double currentPrice, String status, String realEstateCo) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.currentPrice = currentPrice;
        this.status = status;
        this.realEstateCo = realEstateCo;
    }

    // Getters and Setters
    public Long getAuctionId() { return auctionId; }
    public void setAuctionId(Long auctionId) { this.auctionId = auctionId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getFinishDate() { return finishDate; }
    public void setFinishDate(Date finishDate) { this.finishDate = finishDate; }
    public Double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(Double currentPrice) { this.currentPrice = currentPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRealEstateCo() { return realEstateCo; }
    public void setRealEstateCo(String realEstateCo) { this.realEstateCo = realEstateCo; }
    public List<Long> getRealEstateAgents() { return realEstateAgents; }

    public List<Bid> getBids() { return bids; }
    public void setBids(List<Bid> bids) { this.bids = bids; }

    // Get the highest bid
    public Bid getHighestBid() {
        return bids.stream().max((bid1, bid2) -> Double.compare(bid1.getBid(), bid2.getBid())).orElse(null);
    }

    // Get the buyout price
    public Double getBuyOutBid() {
        Bid highestBid = getHighestBid();
        if (highestBid != null) {
            return highestBid.getBid() + 80000;
        }
        return null;
    }

    public Date getLastBidTime() {
        Bid highestBid = getHighestBid();
        if (highestBid != null) {
            return highestBid.getLastBidTime();
        }
        return null;
    }

    public void processBuyout(Buyer buyer) {
        Bid highestBid = getHighestBid();
        if (highestBid != null && getBuyOutBid() != null) {
            this.setStatus("Closed");
            System.out.println("Buyout processed for buyer: " + buyer.getFirstName() + " " + buyer.getLastName());
        }
    }

    public void checkAuctionStatus() {
        Date currentDate = new Date();

        if (getStartDate().after(currentDate)) {
            this.setStatus("Auction starts on " + this.getStartDate());
        } else if (getFinishDate() != null && getFinishDate().before(currentDate)) {
            this.setStatus("Closed");
        } else if (getLastBidTime() != null && (currentDate.getTime() - getLastBidTime().getTime()) > (30 * 60 * 1000)) {
            this.setStatus("Closed");
        } else {
            this.setStatus("Open");
        }
    }


    @Override
    public String toString() {
        return "AuctionEvent{" +
                ", auction Id='" + auctionId + '\'' +
                ", auction name='" + name + '\'' +
                ", start date='" + startDate + '\'' +
                ", finish date='" + finishDate + '\'' +
                ", current price='" + currentPrice + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public boolean checkAuctions(Long auctionId) {
        return this.realEstateAgents.contains(auctionId);
    }
    }

