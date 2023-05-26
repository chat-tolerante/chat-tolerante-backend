package com.chat.app.rest.Model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(schema = "MessagesChat")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Text")
    private String text;

    @Column(name = "Sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "createdDate")
    private Date createdDate;

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
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public Date getCreatedDate(){
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
