package org.example.user.db;

import org.example.user.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JpaUserRepository extends PagingAndSortingRepository<User, Long> {
    User getUserById(Long userId);
}
