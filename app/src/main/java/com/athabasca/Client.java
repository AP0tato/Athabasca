package com.athabasca;

public class Client {
    //This class is incomplete and should be updated once we have more information on what client data is stored
    private Long phoneNumber;
    private String address;
    private String firstName;
    private String lastName;
    private String dateJoined;
    private String email;
    
    public static String[] Categories = {"First Name","Last Name", "Phone number", "Address", "Date Joined","Email"};
    Client(String firstName, String lastName, Long phoneNumber, String address, String dateJoined, String email){
        this.firstName = firstName;
        this.address = address;
        this.lastName = lastName;
        this.dateJoined = dateJoined;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public String getDateJoined() {
        return dateJoined;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Long getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return firstName + "|" + lastName + "|" + phoneNumber + "|" + address + "|" + dateJoined+"|"+email;
    }


    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param dateJoined the dateJoined to set
     */
    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

}
