package com.hotel.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private long id;
    @Column(name = "first_name") // dobra praktyka dla SQL
    private String firstName; // dobra praktyka dla JAVY
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_address")
    private String emailAddress;
    private String address;
    private String country;
    private String state;
    @Column(name = "phone_number")
    private String phoneNumber;
    public long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
