package Excalibur.BidSystem.Controller.dto;

import jakarta.persistence.Temporal;
import java.util.Date;
import jakarta.persistence.TemporalType;


public class AuctionDTO {
    private Long auctionId;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    private Double currentPrice;
    private String status;
    private String realEstateCo;

    public AuctionDTO() {
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
    public String getRealEstateCo() {return realEstateCo;}
    public void setRealEstateCo(String realEstateCo) {this.realEstateCo = realEstateCo;}
}
