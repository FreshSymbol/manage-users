package com.freshsymbol.manageusers.mvc.repository;

import com.freshsymbol.manageusers.mvc.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    void findByEmailReturnTrue() {
        User user = repository.findByEmail("test@mail.ru");

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("test@mail.ru");
    }

    @Test
    void findByEmailReturnNull() {
        User user = repository.findByEmail("test2@mail.ru");
        assertThat(user).isNull();
    }
}