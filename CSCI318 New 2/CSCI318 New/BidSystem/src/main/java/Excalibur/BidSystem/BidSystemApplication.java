package Excalibur.BidSystem;

import Excalibur.BidSystem.Model.*;
import Excalibur.BidSystem.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;


@SpringBootApplication
public class BidSystemApplication {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(BidSystemApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {return builder.build();}


	@Bean
	public CommandLineRunner loadDatabase(AuctionRepository auctionRepository, BidRepository bidRepository) throws Exception {
		return args -> {
			// Insert auctions first
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			Auction entry = new Auction();
			entry.setName("10 Hercules St");
			entry.setStartDate(dateFormat.parse("13-09-2024"));
			entry.setFinishDate(dateFormat.parse("23-09-2024"));
			entry.setCurrentPrice(1500000.00);
			entry.checkAuctionStatus();
			auctionRepository.save(entry);
			System.out.println(auctionRepository.findById(entry.getAuctionId()).orElseThrow());

			Auction entry1 = new Auction();
			entry1.setName("10 Lombok St");
			entry1.setStartDate(dateFormat.parse("24-12-2024"));
			entry1.setFinishDate(dateFormat.parse("12-01-2025"));
			entry1.setCurrentPrice(2000000.00);
			entry1.checkAuctionStatus();
			auctionRepository.save(entry1);
			System.out.println(auctionRepository.findById(entry1.getAuctionId()).orElseThrow());

			// Insert bids after auctions
			Bid bidEntry = new Bid();
			bidEntry.setBid(1250000.00);
			bidEntry.setBuyOutBid(2500000.00);
			bidEntry.setStartBid(1000000.00);
			bidEntry.setBidCounts(1);
			bidEntry.setHighestBid(1250000.00);
			bidRepository.save(bidEntry);
			System.out.println(bidRepository.findById(bidEntry.getBidId()).orElseThrow());

			Bid bidEntry1 = new Bid();
			bidEntry1.setBid(1350000.00);
			bidEntry1.setBuyOutBid(2500000.00);
			bidEntry1.setStartBid(1000000.00);
			bidEntry1.setBidCounts(2);
			bidEntry1.setHighestBid(1350000.00);
			bidRepository.save(bidEntry1);
			System.out.println(bidRepository.findById(bidEntry1.getBidId()).orElseThrow());
		};
	}

}
