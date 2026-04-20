package com.sistemapracticasprofesional.logic.dao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import com.sistemapracticasprofesional.logic.dto.MessageDto;

public class MessageDaoTest {

    private MessageDao messageDao;
    
    public MessageDaoTest() {
    }

    @BeforeEach
    void initializaDao(){

        messageDao = new MessageDao();
        
    }

    @Test
    public void testSendMessage() {

        boolean expetedResult = true;

        MessageDto messageDto = new MessageDto();
        boolean result = messageDao.sendMessage(messageDto);

        assertEquals(expetedResult, result);
        
    }

    @Test
    public void testGetMessageById() {

        MessageDto expetedResult = new MessageDto();
        MessageDto result = messageDao.getMessageById(0);

        assertEquals(expetedResult, result);

    }

    @Test
    public void testGetInboxByUserId() {

        MessageDto messageDto1 = new MessageDto();
        MessageDto messageDto2 = new MessageDto();
        MessageDto messageDto3 = new MessageDto();
        MessageDto messageDto4 = new MessageDto();

        List<MessageDto> expetedResult = new ArrayList<>();

        expetedResult.add(messageDto1);
        expetedResult.add(messageDto2);
        expetedResult.add(messageDto3);
        expetedResult.add(messageDto4);

        List<MessageDto> result = messageDao.getInboxByUserId(0);

        assertEquals(expetedResult, result);

    }

    @Test
    public void testGetSentMessagesByUserId() {

        MessageDto messageDto1 = new MessageDto();
        MessageDto messageDto2 = new MessageDto();
        MessageDto messageDto3 = new MessageDto();
        MessageDto messageDto4 = new MessageDto();

        List<MessageDto> expetedResult = new ArrayList<>();

        expetedResult.add(messageDto1);
        expetedResult.add(messageDto2);
        expetedResult.add(messageDto3);
        expetedResult.add(messageDto4);

        List<MessageDto> result = messageDao.getSentMessagesByUserId(0);

        assertEquals(expetedResult, result);        

    }

    @Test
    public void testDeleteMessage() {

        boolean expetedResult = true;

        boolean result = messageDao.deleteMessage(0);

    }
    
}
