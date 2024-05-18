package com.Murc.Loc;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.Murc.Loc.Controller.api.UserRestApi;
import com.Murc.Loc.Model.User;
import com.Murc.Loc.Service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserRestApiTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserRestApi userRestApi;

    @Test
    public void testFindAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        when(userService.findAllUser()).thenReturn(userList);

        List<User> result = userRestApi.findAllUser();

        assertEquals(userList.size(), result.size());
        verify(userService, times(1)).findAllUser();
    }

    @Test
    public void testSaveUser() {
        User newUser = new User();
        when(userService.saveUser(newUser)).thenReturn(newUser);

        User result = userRestApi.saveUser(newUser);

        assertEquals(newUser, result);
        verify(userService, times(1)).saveUser(newUser);
    }

    @Test
    public void testFindById() {
        Long userId = 1L;
        User user = new User();
        when(userService.findById(userId)).thenReturn(user);

        User result = userRestApi.findById(userId);

        assertEquals(user, result);
        verify(userService, times(1)).findById(userId);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User updatedUser = new User();
        when(userService.updateUser(updatedUser, userId)).thenReturn(updatedUser);

        User result = userRestApi.updateUser(updatedUser, userId);

        assertEquals(updatedUser, result);
        verify(userService, times(1)).updateUser(updatedUser, userId);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        userRestApi.deleteUser(userId);

        verify(userService, times(1)).deleteUser(userId);
    }
}
