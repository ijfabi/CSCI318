package Excalibur.AuctionSystem.Repository;

import Excalibur.AuctionSystem.Model.Event.RealEstateAgentEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateAgentEventRepository extends JpaRepository<RealEstateAgentEvent, Long> {
}
