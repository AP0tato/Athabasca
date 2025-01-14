package com.athabasca;

public class Client {
    //This class is incomplete and should be updated once we have more information on what client data is stored
    private Long phoneNumber;
    private String address;
    private String firstName;
    private String lastName;
    private String dateJoined;
    public static String[] Categories = {"First Name","Last Name", "Phone number", "Address", "Date Joined"};
    Client(String firstName, String lastName, Long phoneNumber, String address, String dateJoined){
        this.firstName = firstName;
        this.address = address;
        this.lastName = lastName;
        this.dateJoined = dateJoined;
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return firstName + "|" + lastName + "|" + phoneNumber + "|" + address + "|" + dateJoined;
    }

}
