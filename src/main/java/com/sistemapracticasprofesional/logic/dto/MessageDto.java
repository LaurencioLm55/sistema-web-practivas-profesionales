
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + messageId;
        result = prime * result + senderUserId;
        result = prime * result + receiverUserId;
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MessageDto other = (MessageDto) obj;
        if (messageId != other.messageId)
            return false;
        if (senderUserId != other.senderUserId)
            return false;
        if (receiverUserId != other.receiverUserId)
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        return true;
    }

    
}
