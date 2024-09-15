package Excalibur.RealEstateSystem.Controller;

import Excalibur.RealEstateSystem.Model.RealEstateAgent;
import Excalibur.RealEstateSystem.Repository.RealEstateAgentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RealEstateAgentController {

    private final RealEstateAgentRepository realEstateAgentRepository;

    // Constructor
    RealEstateAgentController(RealEstateAgentRepository realEstateAgentRepository) {
        this.realEstateAgentRepository = realEstateAgentRepository;
    }

    // Get all agents
    @GetMapping("/realEstateAgents")
    List<RealEstateAgent> all() {
        return realEstateAgentRepository.findAll();
    }

    // Get agent by ID
    @GetMapping("/realEstateAgents/{employeeNo}")
    RealEstateAgent findAgentById(@PathVariable Long employeeNo) {
        return realEstateAgentRepository.findById(employeeNo)
                .orElseThrow(() -> new RuntimeException("Real estate agent not found with id " + employeeNo));
    }

    // Create a new agent
    @PostMapping("/realEstateAgents")
    RealEstateAgent createAgent(@RequestBody RealEstateAgent newAgent) {
        return realEstateAgentRepository.save(newAgent);
    }

    // Update an agent's details
    @PutMapping("/realEstateAgents/{employeeNo}")
    RealEstateAgent updateAgent(@PathVariable Long employeeNo, @RequestBody RealEstateAgent agentDetails) {
        RealEstateAgent agent = realEstateAgentRepository.findById(employeeNo)
                .orElseThrow(() -> new RuntimeException("Real estate agent not found with id " + employeeNo));
        agent.setFirstName(agentDetails.getFirstName());
        agent.setLastName(agentDetails.getLastName());
        agent.setPhone(agentDetails.getPhone());
        agent.setEmail(agentDetails.getEmail());
        agent.setDob(agentDetails.getDob());
        agent.setGender(agentDetails.getGender());
        return realEstateAgentRepository.save(agent);
    }

    // Delete an agent
    @DeleteMapping("/realEstateAgents/{employeeNo}")
    void deleteAgent(@PathVariable Long employeeNo) {
        RealEstateAgent agent = realEstateAgentRepository.findById(employeeNo)
                .orElseThrow(() -> new RuntimeException("Real estate agent not found with id " + employeeNo));
        realEstateAgentRepository.delete(agent);
    }
}
