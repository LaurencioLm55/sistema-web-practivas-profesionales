
package com.sistemapracticasprofesional.logic.dto;

public class MessageDto {

    private int messageId;
    private int senderUserId;
    private int receiverUserId;
    private String subject;
    private String content;

    public MessageDto() {
    }

    public MessageDto(int senderUserId, int receiverUserId, String subject,
                      String content) {
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.subject = subject;
        this.content = content;
    }

    public MessageDto(int messageId, int senderUserId, int receiverUserId,
                      String subject, String content) {
        this.messageId = messageId;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.subject = subject;
        this.content = content;
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
}
