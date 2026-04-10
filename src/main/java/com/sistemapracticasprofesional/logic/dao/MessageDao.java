package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.MessageDto;
import com.sistemapracticasprofesional.logic.exception.DatabaseOperationException;
import com.sistemapracticasprofesional.logic.interfaces.IMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDao implements IMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDao.class);

    private static final String INSERT_QUERY =
            "INSERT INTO mensaje (Id_usuario_remitente, Id_usuario_destinatario, Asunto, Contenido, Fecha_envio, Leido) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BY_ID_QUERY =
            "SELECT * FROM mensaje WHERE Id_mensaje = ?";

    private static final String SELECT_INBOX_QUERY =
            "SELECT * FROM mensaje WHERE Id_usuario_destinatario = ? ORDER BY Fecha_envio DESC";

    private static final String SELECT_SENT_QUERY =
            "SELECT * FROM mensaje WHERE Id_usuario_remitente = ? ORDER BY Fecha_envio DESC";

    private static final String MARK_AS_READ_QUERY =
            "UPDATE mensaje SET Leido = ? WHERE Id_mensaje = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM mensaje WHERE Id_mensaje = ?";

    @Override
    public boolean sendMessage(MessageDto message) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, message.getSenderUserId());
            preparedStatement.setInt(2, message.getReceiverUserId());
            preparedStatement.setString(3, message.getSubject());
            preparedStatement.setString(4, message.getContent());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(message.getSentDate()));
            preparedStatement.setBoolean(6, message.isRead());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error sending message from user {} to user {}",
                    message.getSenderUserId(), message.getReceiverUserId(), e);
            throw new DatabaseOperationException("Error al enviar el mensaje", e);
        }
    }

    @Override
    public MessageDto getMessageById(int messageId) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {

            preparedStatement.setInt(1, messageId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDto(resultSet);
                }
            }

            return null;

        } catch (SQLException e) {
            LOGGER.error("Error getting message with id {}", messageId, e);
            throw new DatabaseOperationException("Error al obtener el mensaje", e);
        }
    }

    @Override
    public List<MessageDto> getInboxByUserId(int receiverUserId) {
        List<MessageDto> messageList = new ArrayList<>();

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INBOX_QUERY)) {

            preparedStatement.setInt(1, receiverUserId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    messageList.add(mapResultSetToDto(resultSet));
                }
            }

            return messageList;

        } catch (SQLException e) {
            LOGGER.error("Error getting inbox for user {}", receiverUserId, e);
            throw new DatabaseOperationException("Error al obtener el buzón", e);
        }
    }

    @Override
    public List<MessageDto> getSentMessagesByUserId(int senderUserId) {
        List<MessageDto> messageList = new ArrayList<>();

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SENT_QUERY)) {

            preparedStatement.setInt(1, senderUserId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    messageList.add(mapResultSetToDto(resultSet));
                }
            }

            return messageList;

        } catch (SQLException e) {
            LOGGER.error("Error getting sent messages for user {}", senderUserId, e);
            throw new DatabaseOperationException("Error al obtener los mensajes enviados", e);
        }
    }

    @Override
    public boolean markMessageAsRead(int messageId) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(MARK_AS_READ_QUERY)) {

            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, messageId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error marking message {} as read", messageId, e);
            throw new DatabaseOperationException("Error al marcar el mensaje como leído", e);
        }
    }

    @Override
    public boolean deleteMessage(int messageId) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setInt(1, messageId);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error deleting message {}", messageId, e);
            throw new DatabaseOperationException("Error al eliminar el mensaje", e);
        }
    }

    private MessageDto mapResultSetToDto(ResultSet resultSet) throws SQLException {
        Timestamp sentTimestamp = resultSet.getTimestamp("Fecha_envio");

        return new MessageDto(
                resultSet.getInt("Id_mensaje"),
                resultSet.getInt("Id_usuario_remitente"),
                resultSet.getInt("Id_usuario_destinatario"),
                resultSet.getString("Asunto"),
                resultSet.getString("Contenido"),
                sentTimestamp != null ? sentTimestamp.toLocalDateTime() : null,
                resultSet.getBoolean("Leido")
        );
    }
}