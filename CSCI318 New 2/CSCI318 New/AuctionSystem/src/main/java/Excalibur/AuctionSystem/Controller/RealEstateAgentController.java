package Excalibur.AuctionSystem.Controller;

import Excalibur.AuctionSystem.Controller.dto.AuctionDTO;
import Excalibur.AuctionSystem.Controller.dto.RealEstateAgentDTO;
import Excalibur.AuctionSystem.Model.RealEstateAgent;
import Excalibur.AuctionSystem.Service.RealEstateAgentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RealEstateAgentController {
    private final RealEstateAgentService realEstateAgentService;

    RealEstateAgentController(RealEstateAgentService realEstateAgentService) {
        this.realEstateAgentService = realEstateAgentService;
    }

    @GetMapping("/realEstateAgents")
    List<RealEstateAgentDTO> allRealEstateAgents() {

        return realEstateAgentService.getAllRealEstateAgents()
                .stream()
                .map(realEstateAgent -> {
                    RealEstateAgentDTO realEstateAgentDTO = new RealEstateAgentDTO();
                    realEstateAgentDTO.setEmployeeNo(realEstateAgent.getEmployeeNo());
                    realEstateAgentDTO.setFirstName(realEstateAgent.getFirstName());
                    realEstateAgentDTO.setLastName(realEstateAgent.getLastName());
                    realEstateAgentDTO.setEmail(realEstateAgent.getEmail());
                    realEstateAgentDTO.setPhone(realEstateAgent.getPhone());
                    realEstateAgentDTO.setDob(realEstateAgent.getDob());
                    realEstateAgentDTO.setGender(realEstateAgent.getGender());
                    return realEstateAgentDTO;
                }).collect(Collectors.toList());
    }

    @GetMapping("/realEstateAgents/{employeeNo}")
    RealEstateAgentDTO findRealEstateAgent(@PathVariable Long employeeNo){
        RealEstateAgentDTO realEstateAgentDTO = new RealEstateAgentDTO();
        RealEstateAgent realEstateAgent = realEstateAgentService.getRealEstateAgent(employeeNo);
        realEstateAgentDTO.setEmployeeNo(realEstateAgent.getEmployeeNo());
        realEstateAgentDTO.setFirstName(realEstateAgent.getFirstName());
        realEstateAgentDTO.setLastName(realEstateAgent.getLastName());
        realEstateAgentDTO.setEmail(realEstateAgent.getEmail());
        realEstateAgentDTO.setPhone(realEstateAgent.getPhone());
        realEstateAgentDTO.setDob(realEstateAgent.getDob());
        realEstateAgentDTO.setGender(realEstateAgent.getGender());
        return realEstateAgentDTO;
    }

    @GetMapping("/realEstateAgents/{employeeNo}/available")
    List<AuctionDTO> auctions(@PathVariable Long employeeNo) {

        //Functional style
        return  realEstateAgentService.getAuction(employeeNo)
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

    @PutMapping("/realEstateAgents/borrow/{employeeNo}/{auctionId}")
    void borrow(@PathVariable Long employeeNo, @PathVariable Long auctionId) {
        realEstateAgentService.borrowRealEstateAgent(employeeNo, auctionId);
    }

    @PutMapping("/realEstateAgents/return/{employeeNo}/{auctionId}")
    void return1(@PathVariable Long employeeNo, @PathVariable Long auctionId) {
        realEstateAgentService.returnRealEstateAgent(employeeNo, auctionId);
    }
}