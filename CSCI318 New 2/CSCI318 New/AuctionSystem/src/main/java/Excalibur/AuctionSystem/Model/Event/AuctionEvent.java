package Excalibur.AuctionSystem.Model.Event;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class AuctionEvent {
    @Id
    @GeneratedValue
    private Long auctionEventId;

    @Column
    private String eventName;

    @Column
    private Long auctionId;

    @Column
    private String name;

    @Column
    private Date startDate;

    @Column
    private Date finishDate;

    @Column
    private Double currentPrice;

    @Column
    private String status;

    @Column
    private Long employeeNo;

    public AuctionEvent(){
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public Long getEmployeeNo() {return employeeNo;}

    public void setEmployeeNo(Long employeeNo) {this.employeeNo = employeeNo;}

    @Override
    public String toString() {
        return "AuctionEvent{" +
                "event_name='" + eventName + '\'' +
                ", auction Id='" + auctionId + '\'' +
                ", auction name='" + name + '\'' +
                ", start date='" + startDate + '\'' +
                ", finish date='" + finishDate + '\'' +
                ", current price='" + currentPrice + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
