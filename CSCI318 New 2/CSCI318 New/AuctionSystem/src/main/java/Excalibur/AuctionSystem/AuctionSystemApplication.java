package Excalibur.AuctionSystem;

import Excalibur.AuctionSystem.Model.Auction;
import Excalibur.AuctionSystem.Repository.AuctionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;


@SpringBootApplication
public class AuctionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionSystemApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner loadDatabaseAuction(AuctionRepository auctionRepository) throws Exception {
		return args -> {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			Auction entry = new Auction();
			entry.setName("10 Hercules St");
			entry.setStartDate(dateFormat.parse("13-09-2024")); // Convert String to Date
			entry.setFinishDate(dateFormat.parse("23-09-2024")); // Convert String to Date
			entry.setCurrentPrice(1500000.00);
			auctionRepository.save(entry);
			System.out.println(auctionRepository.findById(entry.getAuctionId()).orElseThrow());

			Auction entry1 = new Auction();
			entry1.setName("10 Lombok St");
			entry1.setStartDate(dateFormat.parse("24-12-2024")); // Convert String to Date
			entry1.setFinishDate(dateFormat.parse("12-01-2025")); // Convert String to Date
			entry1.setCurrentPrice(2000000.00);
			auctionRepository.save(entry1);
			System.out.println(auctionRepository.findById(entry1.getAuctionId()).orElseThrow());
		};
	}

}
