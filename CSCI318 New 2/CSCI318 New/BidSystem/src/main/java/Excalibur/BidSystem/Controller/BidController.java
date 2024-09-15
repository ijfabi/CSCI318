package Excalibur.BidSystem.Controller;

import Excalibur.BidSystem.Controller.dto.BidDTO;
import Excalibur.BidSystem.Controller.dto.AuctionDTO;
import Excalibur.BidSystem.Model.Bid;
import Excalibur.BidSystem.Service.BidService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bids") // Base URL for all bid-related operations
public class BidController {

    private final BidService bidService;

    BidController(BidService bidService) {
        this.bidService = bidService;
    }

    // Get all bids
    @GetMapping
    public List<BidDTO> findAllBids() {
        return bidService.getAllBids()
                .stream()
                .map(bid -> {
                    BidDTO bidDto = new BidDTO();
                    bidDto.setBidId(bid.getBidId());
                    bidDto.setBid(bid.getBid());
                    bidDto.setBuyOutBid(bid.getBuyOutBid());
                    bidDto.setStartBid(bid.getStartBid());
                    bidDto.setBidCounts(bid.getBidCounts());
                    bidDto.setHighestBid(bid.getHighestBid());
                    bidDto.setLastBidTime(bid.getLastBidTime());
                    return bidDto;
                }).collect(Collectors.toList());
    }

    // Get a bid by ID
    @GetMapping("/{bidId}")
    public BidDTO getBidById(@PathVariable Long bidId) {
        BidDTO bidDto = new BidDTO();
        Bid bid = bidService.getBidById(bidId);
        bidDto.setBidId(bid.getBidId());
        bidDto.setBid(bid.getBid());
        bidDto.setBuyOutBid(bid.getBuyOutBid());
        bidDto.setStartBid(bid.getStartBid());
        bidDto.setBidCounts(bid.getBidCounts());
        bidDto.setHighestBid(bid.getHighestBid());
        bidDto.setLastBidTime(bid.getLastBidTime());
        return bidDto;
    }

    // Get all auctions associated with a bid
    @GetMapping("/{bidId}/available")
    public List<AuctionDTO> auctions(@PathVariable Long bidId) {
        return bidService.getAuctions(bidId)
                .stream()
                .map(auction -> {
                    AuctionDTO auctionDto = new AuctionDTO();
                    auctionDto.setAuctionId(auction.getAuctionId());
                    auctionDto.setName(auction.getName());
                    auctionDto.setStartDate(auction.getStartDate());
                    auctionDto.setFinishDate(auction.getFinishDate());
                    auctionDto.setCurrentPrice(auction.getCurrentPrice());
                    auctionDto.setStatus(auction.getStatus());
                    auctionDto.setRealEstateCo(auction.getRealEstateCo());
                    return auctionDto;
                }).collect(Collectors.toList());
    }

    // Borrow a bid (this method name is a bit unclear, ensure its purpose is well-defined)
    @PutMapping("/borrow/{bidId}/{auctionId}")
    public void borrow(@PathVariable Long bidId, @PathVariable Long auctionId) {
        bidService.borrowBid(bidId, auctionId);
    }

    // Return a bid
    @PutMapping("/return/{bidId}/{auctionId}")
    public void return1(@PathVariable Long bidId, @PathVariable Long auctionId) {
        bidService.returnBid(bidId, auctionId);
    }

    // Link a bid to an auction
    @PutMapping("/{bidId}/linkAuction/{auctionId}")
    public BidDTO linkBidToAuction(@PathVariable Long bidId, @PathVariable Long auctionId) {
        // Call the service to link the bid to the auction
        Bid bid = bidService.createBid(bidId, auctionId);

        // Map the bid to BidDTO to return the response
        BidDTO bidDto = new BidDTO();
        bidDto.setBidId(bid.getBidId());
        bidDto.setBid(bid.getBid());
        bidDto.setBuyOutBid(bid.getBuyOutBid());
        bidDto.setStartBid(bid.getStartBid());
        bidDto.setBidCounts(bid.getBidCounts());
        bidDto.setHighestBid(bid.getHighestBid());
        bidDto.setLastBidTime(bid.getLastBidTime());
        return bidDto;
    }
}
