package com.sistemapracticasprofesional.logic.interfaces;

import com.sistemapracticasprofesional.logic.dto.MessageDto;
import java.util.List;

public interface IMessage {

    boolean sendMessage(MessageDto message);

    MessageDto getMessageById(int messageId);

    List<MessageDto> getInboxByUserId(int receiverUserId);

    List<MessageDto> getSentMessagesByUserId(int senderUserId);

    boolean markMessageAsRead(int messageId);

    boolean deleteMessage(int messageId);
}