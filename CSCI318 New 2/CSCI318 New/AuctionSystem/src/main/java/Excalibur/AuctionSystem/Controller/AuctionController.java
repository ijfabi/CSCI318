package Excalibur.AuctionSystem.Controller;

import Excalibur.AuctionSystem.Controller.dto.AuctionDTO;
import Excalibur.AuctionSystem.Controller.dto.RealEstateAgentDTO;
import Excalibur.AuctionSystem.Model.Auction;
import Excalibur.AuctionSystem.Service.AuctionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuctionController {
    private final AuctionService auctionService;

    AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/auctions")
    List<AuctionDTO> allAuctions() {

        return auctionService.getAllAuctions()
                .stream()
                .map(auction -> {
                    AuctionDTO auctionDto = new AuctionDTO();
                    auctionDto.setAuctionId(auction.getAuctionId());
                    auctionDto.setName(auction.getName());
                    auctionDto.setStartDate(auction.getStartDate());
                    auctionDto.setFinishDate(auction.getFinishDate());
                    auctionDto.setCurrentPrice(auction.getCurrentPrice());
                    auctionDto.setStatus(auction.getStatus());
                    return auctionDto;
                }).collect(Collectors.toList());
    }

    @GetMapping("/auctions/{auctionId}")
    AuctionDTO findAuction(@PathVariable Long auctionId){
        AuctionDTO auctionDto = new AuctionDTO();
        Auction auction = auctionService.getAuction(auctionId);
        auctionDto.setAuctionId(auction.getAuctionId());
        auctionDto.setName(auction.getName());
        auctionDto.setStartDate(auction.getStartDate());
        auctionDto.setFinishDate(auction.getFinishDate());
        auctionDto.setCurrentPrice(auction.getCurrentPrice());
        auctionDto.setStatus(auction.getStatus());
        return auctionDto;
    }

    @GetMapping("/auctions/{auctionId}/available")
    List<RealEstateAgentDTO> realEstateAgents(@PathVariable Long auctionId) {

        //Functional style
        return  auctionService.getRealEstateAgent(auctionId)
                .stream()
                .map(realEstateAgent -> {
                    RealEstateAgentDTO realEstateAgentDto = new RealEstateAgentDTO();
                    realEstateAgentDto.setEmployeeNo(realEstateAgent.getEmployeeNo());
                    realEstateAgentDto.setFirstName(realEstateAgent.getFirstName());
                    realEstateAgentDto.setLastName(realEstateAgent.getLastName());
                    realEstateAgentDto.setEmail(realEstateAgent.getEmail());
                    realEstateAgentDto.setPhone(realEstateAgent.getPhone());
                    realEstateAgentDto.setDob(realEstateAgent.getDob());
                    realEstateAgentDto.setGender(realEstateAgent.getGender());
                    return realEstateAgentDto;
                }).collect(Collectors.toList());
    }

    @PutMapping("/auctions/borrow/{auctionId}/{companyId}")
    void borrow(@PathVariable Long auctionId, @PathVariable Long companyId) {
        auctionService.borrowAuction(auctionId, companyId);
    }

    @PutMapping("/auctions/return/{auctionId}/{companyId}")
    void return1(@PathVariable Long auctionId, @PathVariable Long companyId) {
        auctionService.returnAuction(auctionId, companyId);
    }
}