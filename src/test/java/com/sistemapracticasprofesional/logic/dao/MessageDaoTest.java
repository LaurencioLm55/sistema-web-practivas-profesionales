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
    public void testSendMessageSuccess() {

        boolean expetedResult = true;

        MessageDto messageDto = new MessageDto();
        boolean result = messageDao.sendMessage(messageDto);

        assertEquals(expetedResult, result);
        
    }

    @Test
    public void testGetMessageByIdSuccess() {

        MessageDto expetedResult = new MessageDto(1, 101, 202, "Bienvenida al proyecto", "Hola Valeria, ya quedaste asignada al proyecto de gestión escolar. Cualquier duda con gusto te ayudo.");
        MessageDto result = messageDao.getMessageById(0);

        assertEquals(expetedResult, result);

    }

    @Test
    public void testGetInboxByUserIdSuccess() {

        MessageDto messageDto1 = new MessageDto(1, 202, 101, "Duda sobre actividades", "Buenas tardes, tengo una duda sobre las actividades asignadas esta semana. ¿Podríamos hablar hoy?");
        MessageDto messageDto2 = new MessageDto(2, 303, 101, "Entrega de reporte", "Estimado asesor, adjunto el reporte de avance correspondiente al mes de abril. Quedo en espera de sus comentarios.");
        MessageDto messageDto3 = new MessageDto(3, 404, 101, "Solicitud de reunión", "Buen día, me gustaría agendar una reunión para revisar mi avance en el proyecto. ¿Tiene disponibilidad esta semana?");
        MessageDto messageDto4 = new MessageDto(4, 505, 101, "Problema con acceso al sistema", "Hola, no he podido ingresar al sistema desde ayer. ¿Me podría apoyar con eso por favor?");
        
        List<MessageDto> expetedResult = new ArrayList<>();

        expetedResult.add(messageDto1);
        expetedResult.add(messageDto2);
        expetedResult.add(messageDto3);
        expetedResult.add(messageDto4);

        List<MessageDto> result = messageDao.getInboxByUserId(0);

        assertEquals(expetedResult, result);

    }

    @Test
    public void testGetSentMessagesByUserIdSuccess() {

        MessageDto messageDto1 = new MessageDto(2, 101, 303, "Reunión de seguimiento", "Diego, necesitamos agendar una reunión esta semana para revisar el avance de tu proyecto. ¿Tienes disponibilidad el jueves?");
        MessageDto messageDto2 = new MessageDto(3, 101, 404, "Entrega de reporte", "Sofía, por favor revisa los documentos que te compartí y fírmalos antes del viernes. Es urgente para tu trámite.");
        MessageDto messageDto3 = new MessageDto(4, 101, 505, "Revisión de documentos", "Andrés, recuerda que el plazo para entregar tu reporte final es el próximo lunes. No olvides incluir las evidencias.");
        MessageDto messageDto4 = new MessageDto(5, 101, 606, "Aviso general", "Estimado estudiante, les informamos que la próxima semana habrá una sesión obligatoria de seguimiento de proyectos.");


        List<MessageDto> expetedResult = new ArrayList<>();

        expetedResult.add(messageDto1);
        expetedResult.add(messageDto2);
        expetedResult.add(messageDto3);
        expetedResult.add(messageDto4);

        List<MessageDto> result = messageDao.getSentMessagesByUserId(0);

        assertEquals(expetedResult, result);        

    }

    @Test
    public void testDeleteMessageSuccess() {

        boolean expetedResult = true;

        boolean result = messageDao.deleteMessage(0);

        assertEquals(expetedResult, result);     

    }
    
}
