package com.freshsymbol.manageusers.mvc.service;

import com.freshsymbol.manageusers.mvc.exceptions.UserNotFoundException;
import com.freshsymbol.manageusers.mvc.model.User;
import com.freshsymbol.manageusers.mvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository repository;

    @Transactional
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Transactional
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User with id: %d not found", id)));
    }

    @Transactional
    public void saveUser(User user) {
        user.setDateOfCreate(LocalDate.now());
        repository.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        repository.save(user);
    }

    @Transactional
    public void deleteById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException(String.format("User with id: %d not found", id));
        }
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
