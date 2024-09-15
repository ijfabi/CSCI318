package Excalibur.BidSystem.Service;

import Excalibur.BidSystem.Model.Bid;
import Excalibur.BidSystem.Model.Auction;
import Excalibur.BidSystem.Model.Event.BidEvent;
import Excalibur.BidSystem.Repository.BidRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {


    private final BidRepository bidRepository;
    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    BidService(BidRepository bidRepository, RestTemplate restTemplate,
               ApplicationEventPublisher applicationEventPublisher){
        this.bidRepository = bidRepository;
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<Bid> getAllBids(){ return bidRepository.findAll();
    }

    public Bid getBidById(Long bidId) {
        return bidRepository.findById(bidId).orElseThrow(RuntimeException::new);
    }

    public List<Long> getBids1(Long bidId) {
        return bidRepository.findById(bidId).orElseThrow(RuntimeException::new)
                .getAuctions();
    }

    public List<Auction> getAuctions(Long auctionId) {
        final String url = "http://localhost:8080/auctions/";
        List<Auction> auctions = new ArrayList<>();
        List<Long>  auctionIds = bidRepository.findById(auctionId).orElseThrow(RuntimeException::new)
                .getAuctions();
        for (Long id : auctionIds) {
            auctions.add(restTemplate.getForObject(url + id, Auction.class));
        }
        return auctions;
    }

    public void borrowBid(Long bidId, Long auctionId) {
        Bid bid = bidRepository.findById(auctionId).orElseThrow(RuntimeException::new);
        bid.borrowFrom(bidId);
        bidRepository.save(bid);
    }

    public void returnBid(Long bidId, Long auctionId) {
        Bid bid = bidRepository.findById(bidId).orElseThrow(RuntimeException::new);
        bid.returnTo(auctionId);
        bidRepository.save(bid);
        BidEvent event = new BidEvent();
        event.setEventName("return");
        event.setBidId(bid.getBidId());
        event.setBid(bid.getBid());
        event.setBuyOutBid(bid.getBuyOutBid());
        event.setStartBid(bid.getStartBid());
        event.setBidCounts(bid.getBidCounts());
        event.setHighestBid(bid.getHighestBid());
        event.setAuctionId(auctionId);
        applicationEventPublisher.publishEvent(event);
    }

}

