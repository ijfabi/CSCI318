package Excalibur.AuctionSystem.Service;

import Excalibur.AuctionSystem.Model.Auction;
import Excalibur.AuctionSystem.Model.RealEstateAgent;
import Excalibur.AuctionSystem.Model.Event.RealEstateAgentEvent;
import Excalibur.AuctionSystem.Repository.RealEstateAgentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RealEstateAgentService {


    private final RealEstateAgentRepository realEstateAgentRepository;
    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    RealEstateAgentService(RealEstateAgentRepository realEstateAgentRepository, RestTemplate restTemplate,
                   ApplicationEventPublisher applicationEventPublisher){
        this.realEstateAgentRepository = realEstateAgentRepository;
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<RealEstateAgent> getAllRealEstateAgents(){ return realEstateAgentRepository.findAll();
    }

    public RealEstateAgent getRealEstateAgent(Long employeeNo) {
        return realEstateAgentRepository.findById(employeeNo).orElseThrow(RuntimeException::new);
    }

    public List<Long> getRealEstateAgents1(Long employeeNo) {
        return realEstateAgentRepository.findById(employeeNo).orElseThrow(RuntimeException::new)
                .getAuctions();
    }

    public List<Auction> getAuction(Long auctionId) {
        final String url = "http://localhost:8080/auctions/";
        List<Auction> auctions = new ArrayList<>();
        List<Long>  auctionIds = realEstateAgentRepository.findById(auctionId).orElseThrow(RuntimeException::new)
                .getAuctions();
        for (Long id : auctionIds) {
            auctions.add(restTemplate.getForObject(url + id, Auction.class));
        }
        return auctions;
    }

    public void borrowRealEstateAgent(Long employeeNo, Long auctionId) {
        RealEstateAgent realEstateAgent = realEstateAgentRepository.findById(employeeNo).orElseThrow(RuntimeException::new);
        realEstateAgent.borrowFrom(auctionId);
        realEstateAgentRepository.save(realEstateAgent);
    }

    public void returnRealEstateAgent(Long employeeNo, Long auctionId) {
        RealEstateAgent realEstateAgent = realEstateAgentRepository.findById(employeeNo).orElseThrow(RuntimeException::new);
        realEstateAgent.returnTo(auctionId);
        realEstateAgentRepository.save(realEstateAgent);
        RealEstateAgentEvent event = new RealEstateAgentEvent();
        event.setEventName("return");
        event.setEmployeeNo(realEstateAgent.getEmployeeNo());
        event.setFirstName(realEstateAgent.getFirstName());
        event.setLastName(realEstateAgent.getLastName());
        event.setPhone(realEstateAgent.getPhone());
        event.setEmail(realEstateAgent.getEmail());
        event.setDob(realEstateAgent.getDob());
        event.setGender(realEstateAgent.getGender());
        applicationEventPublisher.publishEvent(event);
    }

}
