package Excalibur.RealEstateSystem;

import Excalibur.RealEstateSystem.Model.*;
import Excalibur.RealEstateSystem.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RealEstateSystemApplication {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(RealEstateSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadDatabase(
			RealEstateAgentRepository realEstateAgentRepository,
			RealEstateCoRepository realEstateCoRepository
	) {
		return args -> {

			// Create and save a RealEstateCo
			RealEstateCo realEstateCo = new RealEstateCo();
			realEstateCo.setName("Wollongong Real Estate");
			realEstateCo.setAddress("123 Main St, Wollongong");
			realEstateCo.setPhone("555-555-5555");
			realEstateCo.setEmail("contact@wollongongre.com");
			realEstateCoRepository.save(realEstateCo);
			System.out.println("RealEstateCo{id=" + realEstateCo.getCompanyId() + ", name='" + realEstateCo.getName() + "', address='" + realEstateCo.getAddress() + "', phone='" + realEstateCo.getPhone() + "', email='" + realEstateCo.getEmail() + "'}");

			// Create and save a RealEstateAgent and associate it with the RealEstateCo
			RealEstateAgent agent = new RealEstateAgent();
			agent.setFirstName("Jane");
			agent.setLastName("Smith");
			agent.setPhone("555-555-5555");
			agent.setEmail("jane.smith@example.com");
			agent.setRealEstateCo(realEstateCo);  // Assign the agent to the real estate company
			realEstateAgentRepository.save(agent);
			System.out.println("RealEstateAgent{id=" + agent.getEmployeeNo() + ", name='" + agent.getFirstName() + " " + agent.getLastName() + "', phone='" + agent.getPhone() + "', email='" + agent.getEmail() + "', company='" + agent.getRealEstateCo().getName() + "'}");
			

		};
	}
}
