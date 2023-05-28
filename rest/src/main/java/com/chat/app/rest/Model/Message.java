package com.chat.app.rest.Model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(schema = "MessagesChat")
public class Message implements Comparable<Message> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Text")
    private String text;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @Column(name = "createdDate")
    private Timestamp createdDate;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int compareTo(Message m) {
        if (getCreatedDate() == null || m.getCreatedDate() == null) {
            return 0;
        }
        return getCreatedDate().compareTo(m.getCreatedDate());
    }
}
