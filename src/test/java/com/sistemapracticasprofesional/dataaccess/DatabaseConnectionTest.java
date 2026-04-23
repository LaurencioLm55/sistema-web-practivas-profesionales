package com.sistemapracticasprofesional.dataaccess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Connection;

public class DatabaseConnectionTest {

    @Test
    public void testGetConnection() {
       Connection connection = DatabaseConnection.getConnection();
       assertNotNull(connection); 
       DatabaseConnection.closeConnection();
    }

    @Test
    public void testCloseConnection() {
    }
    
}
