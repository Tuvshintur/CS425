package edu.mum.cs.cs425.elibraryapp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bookid")
    private int bookId;

    private String title;
    private float fee;

    @Column(name="datepublished")
    private LocalDate datePublished;

    public Book() {
    }

    public Book(String title, float fee, LocalDate datePublished) {
        this.title = title;
        this.fee = fee;
        this.datePublished = datePublished;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", fee=" + fee + ", datePublished=" + datePublished
                + "]";
    }

}
