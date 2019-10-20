package mum.edu.cs.cs425.etdm.model;

import mum.edu.cs.cs425.etdm.model.validator.UniqueSSN;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long athleteId;

    @UniqueSSN(message = "* SSN is required")
    @Column(name = "SSN", unique = true, nullable = false)
    private String ssn;

    @NotBlank(message = "* fullName is required")
    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @NotNull(message = "* date of birth is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull(message = "* date of registration is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfRegistration;

    @Digits(message = "* totalNumberOfMedalsWon is number", fraction = 0, integer = 9)
    private int totalNumberOfMedalsWon;

    @Column(name = "monthlySalary", nullable = false)
    private double monthlySalary;

    @Email
    private String eMailAddress;

    public Athlete() {
    }

    public Athlete( String ssn, @NotBlank(message = "* fullName is required") String fullName, String phoneNumber, @NotNull(message = "* date of birth is required") Date dateOfBirth, @NotNull(message = "* date of registration is required") LocalDate dateOfRegistration, @Digits(message = "* totalNumberOfMedalsWon is number", fraction = 0, integer = 9) int totalNumberOfMedalsWon, double monthlySalary, @Email String eMailAddress) {
        this.ssn = ssn;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
        this.totalNumberOfMedalsWon = totalNumberOfMedalsWon;
        this.monthlySalary = monthlySalary;
        this.eMailAddress = eMailAddress;
    }

    public long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(long athleteId) {
        this.athleteId = athleteId;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public int getTotalNumberOfMedalsWon() {
        return totalNumberOfMedalsWon;
    }

    public void setTotalNumberOfMedalsWon(int totalNumberOfMedalsWon) {
        this.totalNumberOfMedalsWon = totalNumberOfMedalsWon;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }
}
