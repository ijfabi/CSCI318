package Excalibur.BidSystem.Controller;

import Excalibur.BidSystem.Repository.BuyerRepository;
import Excalibur.BidSystem.Model.Buyer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuyerController {

    private final BuyerRepository buyerRepository;

    BuyerController(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    @GetMapping("/buyers")
    List<Buyer> findAllBuyers() {
        return buyerRepository.findAll();
    }

    @GetMapping("/buyers/{id}")
    Buyer getBuyerById(@PathVariable Long id) {
        return buyerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping("/buyers")
    Buyer createBid(@RequestBody Buyer newbuyer) {
        return buyerRepository.save(newbuyer);
    }

    @PutMapping("/buyers/{id}")
    Buyer updateBuyer(@PathVariable Long id, @RequestBody Buyer newBuyer) {
        return buyerRepository.findById(id)
                .map(buyers -> {
                    buyers.setFirstName(newBuyer.getFirstName());
                    buyers.setLastName(newBuyer.getLastName());
                    buyers.setPhone(newBuyer.getPhone());
                    buyers.setAddress(newBuyer.getAddress());
                    buyers.setEmail(newBuyer.getEmail());
                    return buyerRepository.save(buyers);
                })
                .orElseThrow(RuntimeException::new);
    }

    @DeleteMapping("/buyers/{id}")
    void deleteBuyer(@PathVariable Long id) {
        Buyer buyer = buyerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        buyerRepository.delete(buyer);
    }
}