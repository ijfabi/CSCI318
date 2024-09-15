package Excalibur.AuctionSystem.Repository;

import Excalibur.AuctionSystem.Model.Event.AuctionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionEventRepository extends JpaRepository<AuctionEvent, Long> {
}