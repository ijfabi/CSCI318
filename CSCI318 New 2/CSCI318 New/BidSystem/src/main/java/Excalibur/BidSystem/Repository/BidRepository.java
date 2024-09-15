package Excalibur.BidSystem.Repository;

import Excalibur.BidSystem.Model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {

}
