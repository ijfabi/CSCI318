package Excalibur.AuctionSystem.Model;

import Excalibur.AuctionSystem.Model.Event.RealEstateAgentEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class RealEstateAgent extends AbstractAggregateRoot<RealEstateAgent>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeNo;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dob;
    private String gender;


    @Column
    @ElementCollection(fetch = FetchType.EAGER, targetClass=Long.class)
    private List<Long> auctions = new ArrayList<>();

    // Constructors
    public RealEstateAgent() {}

    public RealEstateAgent(String firstName, String lastName, String email, String phone, String dob, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
    }

    // Getters and Setters
    public Long getEmployeeNo() { return employeeNo; }
    public void setEmployeeNo(Long employeeNo) { this.employeeNo = employeeNo; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public List<Long> getAuctions() {return auctions;}

    @Override
    public String toString() {
        return "RealEstateAgentEvent{" +
                ", employee No='" + employeeNo + '\'' +
                ", first name='" + firstName + '\'' +
                ", last name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public boolean checkRealEstateAgents(Long employeeNo) {return this.auctions.contains(employeeNo);
    }

    public void borrowFrom(Long employeeNo) {
        if (this.checkRealEstateAgents(employeeNo)) {
            this.auctions.remove(employeeNo);
            RealEstateAgentEvent event = new RealEstateAgentEvent();
            event.setEventName("borrow");
            event.setEmployeeNo(employeeNo);
            event.setFirstName(this.getFirstName());
            event.setLastName(this.getLastName());
            event.setEmail(this.getEmail());
            event.setPhone(this.getPhone());
            event.setDob(this.getDob());
            event.setGender(this.getGender());
            /**
             * Method to register the event
             * @param event
             **/
            registerEvent(event);
        }
    }

    public void returnTo(Long employeeNo) {
        this.auctions.add(employeeNo);
    }
}
