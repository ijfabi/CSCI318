package Excalibur.AuctionSystem.Service;

import Excalibur.AuctionSystem.Model.Event.AuctionEvent;
import Excalibur.AuctionSystem.Repository.AuctionEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {

    private final AuctionEventRepository auctionEventRepository;

    EventHandler(AuctionEventRepository auctionEventRepository) {
        this.auctionEventRepository = auctionEventRepository;
    }

    @TransactionalEventListener
    public void handleBorrowEvent(AuctionEvent auctionEvent){
        auctionEventRepository.save(auctionEvent);
        System.out.println(auctionEvent);
    }

    @EventListener
    public void handleReturnEvent(AuctionEvent auctionEvent) {
        auctionEventRepository.save(auctionEvent);
        System.out.println(auctionEvent);
    }
}
