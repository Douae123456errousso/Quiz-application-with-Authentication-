package servies;
import servies.UserServices;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServicesTest {

    @Test
    public void testSaveUser_WhenUserAlreadyExists() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");

        try (MockedStatic<UserDAO> mockedDAO = mockStatic(UserDAO.class)) {
            mockedDAO.when(() -> UserDAO.isExist("test@example.com", "Users"))
                     .thenReturn(true);

            int result = UserServices.saveUser(user);

            assertEquals(-1, result); // User already exists
        }
    }

    @Test
    public void testSaveUser_WhenUserSavedSuccessfully() throws Exception {
        User user = new User();
        user.setEmail("test2@example.com");

        try (MockedStatic<UserDAO> mockedDAO = mockStatic(UserDAO.class)) {
            mockedDAO.when(() -> UserDAO.isExist("test2@example.com", "Users"))
                     .thenReturn(false);
            mockedDAO.when(() -> UserDAO.saveUser(user))
                     .thenReturn(1);

            int result = UserServices.saveUser(user);

            assertEquals(1, result); // Success
        }
    }

    @Test
    public void testSaveUser_WhenSaveFails() throws Exception {
        User user = new User();
        user.setEmail("fail@example.com");

        try (MockedStatic<UserDAO> mockedDAO = mockStatic(UserDAO.class)) {
            mockedDAO.when(() -> UserDAO.isExist("fail@example.com", "Users"))
                     .thenReturn(false);
            mockedDAO.when(() -> UserDAO.saveUser(user))
                     .thenReturn(0);

            int result = UserServices.saveUser(user);

            assertEquals(-2, result); // Save failed
        }
    }

    @Test
    public void testSaveUser_WhenSQLExceptionOccurs() throws Exception {
        User user = new User();
        user.setEmail("error@example.com");

        try (MockedStatic<UserDAO> mockedDAO = mockStatic(UserDAO.class)) {
            mockedDAO.when(() -> UserDAO.isExist("error@example.com", "Users"))
                     .thenThrow(new SQLException("DB error"));

            int result = UserServices.saveUser(user);

            assertEquals(-3, result); // SQL exception
        }
    }
}