package Excalibur.AuctionSystem.Controller.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;


public class AuctionDTO {
    private Long auctionId;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    private Double currentPrice;
    private String status;
    private List<Long> realEstateAgentIds;

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
    public List<Long> getRealEstateAgentIds() { return realEstateAgentIds; }
    public void setRealEstateAgentIds(List<Long> realEstateAgentIds) { this.realEstateAgentIds = realEstateAgentIds; }
}
