package Excalibur.AuctionSystem.Service;

import Excalibur.AuctionSystem.Model.Auction;
import Excalibur.AuctionSystem.Model.RealEstateAgent;
import Excalibur.AuctionSystem.Model.Event.AuctionEvent;
import Excalibur.AuctionSystem.Repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public AuctionService(AuctionRepository auctionRepository, RestTemplate restTemplate,
                          ApplicationEventPublisher applicationEventPublisher) {
        this.auctionRepository = auctionRepository;
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Auction createAuction(Auction auction, Long realEstateAgentId) {
        final String url = "http://localhost:8082/realEstateAgents/" + realEstateAgentId;
        RealEstateAgent realEstateAgent = restTemplate.getForObject(url, RealEstateAgent.class);

        if (realEstateAgent != null) {
            auction.getRealEstateAgents().add(realEstateAgent.getEmployeeNo());
            auctionRepository.save(auction);
            return auction;
        } else {
            throw new RuntimeException("Real estate agent not found with id: " + realEstateAgentId);
        }
    }

    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    public Auction getAuction(Long auctionId) {
        return auctionRepository.findById(auctionId).orElseThrow(() ->
                new RuntimeException("Auction not found with id: " + auctionId));
    }

    public List<Long> getAuctions1(Long auctionId) {
        return auctionRepository.findById(auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found with id: " + auctionId))
                .getRealEstateAgents();
    }

    public List<RealEstateAgent> getRealEstateAgent(Long auctionId) {
        final String url = "http://localhost:8082/realEstateAgents/";
        List<RealEstateAgent> realEstateAgents = new ArrayList<>();
        List<Long> employeeNos = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found with id: " + auctionId))
                .getRealEstateAgents();
        for (Long id : employeeNos) {
            RealEstateAgent agent = restTemplate.getForObject(url + id, RealEstateAgent.class);
            if (agent != null) {
                realEstateAgents.add(agent);
            }
        }
        return realEstateAgents;
    }

    public void borrowAuction(Long auctionId, Long employeeNo) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found with id: " + auctionId));
        auction.borrowFrom(employeeNo);
        auctionRepository.save(auction);
    }

    public void returnAuction(Long auctionId, Long employeeNo) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found with id: " + auctionId));
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
