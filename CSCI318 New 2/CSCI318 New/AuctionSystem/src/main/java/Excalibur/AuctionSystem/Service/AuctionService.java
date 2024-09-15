package Excalibur.AuctionSystem.Service;

import Excalibur.AuctionSystem.Model.Auction;
import Excalibur.AuctionSystem.Model.RealEstateAgent;
import Excalibur.AuctionSystem.Model.Event.AuctionEvent;
import Excalibur.AuctionSystem.Repository.AuctionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {


    private final AuctionRepository auctionRepository;
    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    AuctionService(AuctionRepository auctionRepository, RestTemplate restTemplate,
                ApplicationEventPublisher applicationEventPublisher){
        this.auctionRepository = auctionRepository;
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<Auction> getAllAuctions(){ return auctionRepository.findAll();
    }

    public Auction getAuction(Long auctionId) {
        return auctionRepository.findById(auctionId).orElseThrow(RuntimeException::new);
    }

    public List<Long> getAuctions1(Long auctionId) {
        return auctionRepository.findById(auctionId).orElseThrow(RuntimeException::new)
                .getRealEstateAgents();
    }

    public List<RealEstateAgent> getRealEstateAgent(Long employeeNo) {
        final String url = "http://localhost:8082/realEstateAgents/";
        List<RealEstateAgent> realEstateAgents = new ArrayList<>();
        List<Long>  employeeNos = auctionRepository.findById(employeeNo).orElseThrow(RuntimeException::new)
                .getRealEstateAgents();
        for (Long id : employeeNos) {
            realEstateAgents.add(restTemplate.getForObject(url + id, RealEstateAgent.class));
        }
        return realEstateAgents;
    }

    public void borrowAuction(Long auctionId, Long employeeNo) {
        Auction auction = auctionRepository.findById(auctionId).orElseThrow(RuntimeException::new);
        auction.borrowFrom(employeeNo);
        auctionRepository.save(auction);
    }

    public void returnAuction(Long auctionId, Long employeeNo) {
        Auction auction = auctionRepository.findById(auctionId).orElseThrow(RuntimeException::new);
        auction.returnTo(employeeNo);
        auctionRepository.save(auction);
        AuctionEvent event = new AuctionEvent();
        event.setEventName("return");
        event.setAuctionId(auction.getAuctionId());
        event.setName(auction.getName());
        event.setStartDate(auction.getStartDate());
        event.setFinishDate(auction.getFinishDate());
        event.setCurrentPrice(auction.getCurrentPrice());
        event.setStatus(auction.getStatus());
        event.setEmployeeNo(employeeNo);
        applicationEventPublisher.publishEvent(event);
    }

}
