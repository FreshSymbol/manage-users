package com.freshsymbol.manageusers.mvc.service;

import com.freshsymbol.manageusers.mvc.exceptions.UserNotFoundException;
import com.freshsymbol.manageusers.mvc.model.User;
import com.freshsymbol.manageusers.mvc.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private final Long ID = 132L;
    private User user;
    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(ID);
        user.setEmail("test@mail.ru");
    }

    @Test
    void testGetAllUsers() {
        List<User> expectedList = new ArrayList<>();
        User firstUser = new User();
        User secondUser = new User();
        expectedList.add(firstUser);
        expectedList.add(secondUser);
        when(repository.findAll()).thenReturn(expectedList);
        List<User> actualList = service.getAllUsers();
        assertThat(actualList).isEqualTo(expectedList);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetUserByIdIfUserFound() {
        when(repository.findById(ID)).thenReturn(Optional.of(user));
        User actualUser = service.getUserById(ID);
        assertThat(actualUser).isEqualTo(user);
        verify(repository, times(1)).findById(ID);
    }

    @Test
    void testGetUserByIdIfUserNotFound() {
        assertThrows(UserNotFoundException.class, () ->
                service.getUserById(0L));
        verify(repository, never()).findById(ID);
    }

    @Test
    void testSaveUser() {
        when(repository.save(user)).thenReturn(user);
        service.saveUser(user);
        assertThat(LocalDate.now()).isEqualTo(user.getDateOfCreate());
        verify(repository, times(1)).save(user);
    }

    @Test
    void testFindByEmail() {
        when(repository.findByEmail(user.getEmail())).thenReturn(user);
        User expectUser = service.findByEmail(user.getEmail());
        assertThat(user).isEqualTo(expectUser);
        verify(repository, times(1)).findByEmail(user.getEmail());
    }

    @Test
    void testUpdateUser() {
        when(repository.save(user)).thenReturn(user);
        service.updateUser(user);
        verify(repository, times(1)).save(user);
    }

    @Test
    void testDeleteById() {
        when(repository.findById(ID)).thenReturn(Optional.of(user));
        service.deleteById(ID);
        verify(repository, times(1)).findById(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    void testDeleteByIdNotFoundUser() {
        when(repository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () ->
                service.deleteById(ID));
        verify(repository, times(1)).findById(ID);
        verify(repository, never()).deleteById(ID);
    }
}