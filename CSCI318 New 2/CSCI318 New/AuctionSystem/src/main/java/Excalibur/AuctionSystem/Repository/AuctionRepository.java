package Excalibur.AuctionSystem.Repository;

import Excalibur.AuctionSystem.Model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
   
}
