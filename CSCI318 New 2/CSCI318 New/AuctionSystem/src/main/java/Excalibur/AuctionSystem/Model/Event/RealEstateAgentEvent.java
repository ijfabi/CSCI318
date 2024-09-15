package Excalibur.AuctionSystem.Model.Event;

import jakarta.persistence.*;

@Entity
public class RealEstateAgentEvent {
    @Id
    @GeneratedValue
    private Long realEstateAgentEventId;

    @Column
    private String eventName;

    @Column
    private Long employeeNo;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String dob;

    @Column
    private String gender;

    public RealEstateAgentEvent(){
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getEmployeeNo() { return employeeNo; }

    public void setEmployeeNo(Long employeeNo) {this.employeeNo = employeeNo;}

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
}

