package Excalibur.BidSystem.Repository;

import Excalibur.BidSystem.Model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
   
}
