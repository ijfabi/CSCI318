package Excalibur.RealEstateSystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class RealEstateAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeNo;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dob;  // Date of Birth
    private String gender;

    @ManyToOne
    @JsonIgnore  // To avoid circular references during serialization
    private RealEstateCo realEstateCo;  // Reference to the real estate company

    public RealEstateAgent() {}

    // Getters and Setters
    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RealEstateCo getRealEstateCo() {
        return realEstateCo;
    }

    public void setRealEstateCo(RealEstateCo realEstateCo) {
        this.realEstateCo = realEstateCo;
    }



    // Override toString for better logging/debugging
    @Override
    public String toString() {
        return "RealEstateAgent{id=" + employeeNo +
                ", name='" + firstName + " " + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", company=" + (realEstateCo != null ? realEstateCo.getName() : "None") +
                '}';
    }
}
