package Excalibur.BidSystem.Repository;

import Excalibur.BidSystem.Model.Event.BidEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidEventRepository extends JpaRepository<BidEvent, Long> {
}