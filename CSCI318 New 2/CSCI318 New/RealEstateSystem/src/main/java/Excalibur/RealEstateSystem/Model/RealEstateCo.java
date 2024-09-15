package Excalibur.RealEstateSystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class RealEstateCo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    private String name;
    private String address;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "realEstateCo")
    @JsonIgnore  // To avoid circular references during JSON serialization
    private List<RealEstateAgent> agents;  // List of agents working for the company

    @OneToMany(mappedBy = "realEstateCo")
    @JsonIgnore  // To avoid circular references during JSON serialization
    private List<RealEstateAgent> realEstateAgents;  // Auctions hosted by the real estate company

    public RealEstateCo() {}

    // Getters and Setters

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RealEstateAgent> getAgents() {
        return agents;
    }

    public void setAgents(List<RealEstateAgent> agents) {
        this.agents = agents;
    }


    @Override
    public String toString() {
        return "RealEstateCo{id=" + companyId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", agentCount=" + (agents != null ? agents.size() : 0)+
                '}';
    }
}
