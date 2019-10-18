package mum.edu.cs.cs425.bankingsystem.model;

import mum.edu.cs.cs425.bankingsystem.model.validator.UniqueCustomerNumber;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @Digits(message = "* customer number is number", fraction = 2, integer = 9)
    @UniqueCustomerNumber(message = "* customer number is required")
    @Column(name = "customerNumber", nullable = false, unique = true)
    private long customerNumber;

    @NotBlank(message = "* firstName is required")
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @NotBlank(message = "* lastName is required")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Email
    @NotBlank(message = "* emailAddress is required")
    @Column(name = "emailAddress", nullable = false)
    private String emailAddress;

    @NotBlank(message = "* contactPhoneNumber is required")
    @Column(name = "contactPhoneNumber", nullable = false)
    private String contactPhoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accountList;

    public Customer() {
    }

    public Customer(@Digits(message = "* customer number is required", fraction = 0, integer = 9) long customerNumber, @NotBlank(message = "* firstName is required") String firstName, @NotBlank(message = "* middleName is required") String middleName, @NotBlank(message = "* lastName is required") String lastName, @NotBlank(message = "* emailAddress is required") String emailAddress, @NotBlank(message = "* contactPhoneNumber is required") String contactPhoneNumber, Date dateOfBirth) {
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.contactPhoneNumber = contactPhoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public String toString() {
        return this.getCustomerNumber() + " - "  + this.getFirstName() + " " + this.getLastName();
    }
}
