package Excalibur.AuctionSystem.Controller.dto;



public class RealEstateAgentDTO {
    private Long employeeNo;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private String company;
    public RealEstateAgentDTO() {

    }


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
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
}

