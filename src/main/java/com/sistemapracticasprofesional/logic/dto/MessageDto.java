
package com.sistemapracticasprofesional.logic.dto;

import java.time.LocalDateTime;

public class MessageDto {

    private int messageId;
    private int senderUserId;
    private int receiverUserId;
    private String subject;
    private String content;
    private LocalDateTime sentDate;
    private boolean read;

    public MessageDto() {
    }

    public MessageDto(int senderUserId, int receiverUserId, String subject,
                      String content, LocalDateTime sentDate, boolean read) {
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.subject = subject;
        this.content = content;
        this.sentDate = sentDate;
        this.read = read;
    }

    public MessageDto(int messageId, int senderUserId, int receiverUserId,
                      String subject, String content, LocalDateTime sentDate, boolean read) {
        this.messageId = messageId;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.subject = subject;
        this.content = content;
        this.sentDate = sentDate;
        this.read = read;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public int getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(int receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}