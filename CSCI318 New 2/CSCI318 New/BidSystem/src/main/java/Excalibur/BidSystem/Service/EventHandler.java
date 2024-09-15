package Excalibur.BidSystem.Service;

import Excalibur.BidSystem.Model.Event.BidEvent;
import Excalibur.BidSystem.Repository.BidEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {

    private final BidEventRepository bidEventRepository;

    EventHandler(BidEventRepository bidEventRepository) {
        this.bidEventRepository = bidEventRepository;
    }

    @TransactionalEventListener
    public void handleBorrowEvent(BidEvent bidEvent){
        bidEventRepository.save(bidEvent);
        System.out.println(bidEvent);
    }

    @EventListener
    public void handleReturnEvent(BidEvent bidEvent) {
        bidEventRepository.save(bidEvent);
        System.out.println(bidEvent);
    }
}

