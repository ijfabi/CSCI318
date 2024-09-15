package Excalibur.AuctionSystem.Model;

import Excalibur.AuctionSystem.Model.Event.AuctionEvent;
import org.springframework.data.domain.AbstractAggregateRoot;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
public class Auction extends AbstractAggregateRoot<Auction>{
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

    @Column
    @ElementCollection(fetch = FetchType.EAGER, targetClass=Long.class)
    private List<Long> realEstateAgents = new ArrayList<>();

    public Auction() {}

    public Auction(String name, Date startDate, Date finishDate, Double currentPrice, String status) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.currentPrice = currentPrice;
        this.status = status;
    }

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
    public List<Long> getRealEstateAgents() {return realEstateAgents;}


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

    public void borrowFrom(long auctionId) {
        if (this.checkAuctions(auctionId)) {
            this.realEstateAgents.remove(auctionId);
            AuctionEvent event = new AuctionEvent();
            event.setEventName("borrow");
            event.setAuctionId(auctionId);
            event.setName(this.getName());
            event.setStartDate(this.getStartDate());
            event.setFinishDate(this.getFinishDate());
            event.setCurrentPrice(this.getCurrentPrice());
            event.setStatus(this.getStatus());
            /**
             * Method to register the event
             * @param event
             **/
            registerEvent(event);
        }
    }

    public void returnTo(long auctionId) {
        this.realEstateAgents.add(auctionId);
    }
    public void addRealEstateAgent(Long agentId) {
        this.realEstateAgents.add(agentId);
    }
}
