package Excalibur.RealEstateSystem.Controller;

import Excalibur.RealEstateSystem.Model.RealEstateCo;
import Excalibur.RealEstateSystem.Model.RealEstateAgent;
import Excalibur.RealEstateSystem.Repository.RealEstateCoRepository;
import Excalibur.RealEstateSystem.Repository.RealEstateAgentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RealEstateCoController {

    private final RealEstateCoRepository realEstateCoRepository;
    private final RealEstateAgentRepository realEstateAgentRepository;

    public RealEstateCoController(RealEstateCoRepository realEstateCoRepository, RealEstateAgentRepository realEstateAgentRepository) {
        this.realEstateCoRepository = realEstateCoRepository;
        this.realEstateAgentRepository = realEstateAgentRepository;
    }

    @GetMapping("/realEstateCos")
    public List<RealEstateCo> getAllRealEstateCos() {
        return realEstateCoRepository.findAll();
    }

    @GetMapping("/realEstateCos/{companyId}")
    public RealEstateCo getRealEstateCoById(@PathVariable Long companyId) {
        return realEstateCoRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("RealEstateCo not found with id: " + companyId));
    }

    @PostMapping("/realEstateCos")
    public RealEstateCo createRealEstateCo(@RequestBody RealEstateCo realEstateCo) {
        return realEstateCoRepository.save(realEstateCo);
    }

    @PutMapping("/realEstateCos/{companyId}")
    public RealEstateCo updateRealEstateCo(@PathVariable Long companyId, @RequestBody RealEstateCo updatedRealEstateCo) {
        RealEstateCo realEstateCo = realEstateCoRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("RealEstateCo not found with id: " + companyId));
        realEstateCo.setName(updatedRealEstateCo.getName());
        realEstateCo.setAddress(updatedRealEstateCo.getAddress());
        realEstateCo.setPhone(updatedRealEstateCo.getPhone());
        realEstateCo.setEmail(updatedRealEstateCo.getEmail());
        return realEstateCoRepository.save(realEstateCo);
    }

    @DeleteMapping("/realEstateCos/{companyId}")
    public void deleteRealEstateCo(@PathVariable Long companyId) {
        RealEstateCo realEstateCo = realEstateCoRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("RealEstateCo not found with id: " + companyId));
        realEstateCoRepository.delete(realEstateCo);
    }

    // Endpoint to add an agent to a company
    @PostMapping("/realEstateCos/{companyId}/agents")
    public RealEstateAgent addAgentToCompany(@PathVariable Long companyId, @RequestBody RealEstateAgent agent) {
        RealEstateCo company = realEstateCoRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("RealEstateCo not found with id: " + companyId));
        agent.setRealEstateCo(company);
        return realEstateAgentRepository.save(agent);
    }
}
