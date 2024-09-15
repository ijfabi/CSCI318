package Excalibur.BidSystem.Controller;

import Excalibur.BidSystem.Controller.dto.BidDTO;
import Excalibur.BidSystem.Controller.dto.AuctionDTO;
import Excalibur.BidSystem.Model.Bid;
import Excalibur.BidSystem.Service.BidService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BidController {

    private final BidService bidService;

    BidController(BidService bidService) {
        this.bidService = bidService;
    }


    @GetMapping("/bids")
    List<BidDTO> findAllBids() {
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
                    return bidDto;
                }).collect(Collectors.toList());
    }

    @GetMapping("/bids/{bidId}")
    BidDTO getBidById(@PathVariable Long bidId) {
        BidDTO bidDto = new BidDTO();
        Bid bid = bidService.getBidById(bidId);
        bidDto.setBidId(bid.getBidId());
        bidDto.setBid(bid.getBid());
        bidDto.setBuyOutBid(bid.getBuyOutBid());
        bidDto.setStartBid(bid.getStartBid());
        bidDto.setBidCounts(bid.getBidCounts());
        bidDto.setHighestBid(bid.getHighestBid());
        return bidDto;
    }

    @GetMapping("/bids/{bidId}/available")
    List<AuctionDTO> auctions(@PathVariable Long bidId) {

        //Functional style
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

    @PutMapping("/bids/borrow/{bidId}/{auctionId}")
    void borrow(@PathVariable Long bidId, @PathVariable Long auctionId) {
        bidService.borrowBid(bidId, auctionId);
    }

    @PutMapping("/bids/return/{bidId}/{auctionId}")
    void return1(@PathVariable Long bidId, @PathVariable Long auctionId) {
        bidService.returnBid(bidId, auctionId);
    }

}