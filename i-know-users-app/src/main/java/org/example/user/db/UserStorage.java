package org.example.user.db;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class UserStorage {
    @Autowired
    JpaUserRepository jpaUserRepository;

    public User getUserById(Long userId) {
        return jpaUserRepository.getUserById(userId);

    }
}
