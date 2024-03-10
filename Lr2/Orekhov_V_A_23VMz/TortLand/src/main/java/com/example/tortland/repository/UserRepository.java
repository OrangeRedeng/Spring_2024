package com.example.tortland.repository;

import com.example.tortland.model.City;
import com.example.tortland.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends GenericRepository<User>{

    Page<User> findAllByRoleId(Long id, Pageable pageable);

    @Query("SELECT m FROM User m WHERE m.role.id = :id and m.city= :city and m.isDeleted = false")
    Page<User> findAllByRoleIdAndCity(@Param(value = "id") Long id, @Param(value = "city") City city, Pageable pageable);
    List<User> findAllByStatus(String status);

    @Query(nativeQuery = true, value = """
    select * from users where login = :login and is_deleted = false
  """)
    User findUserByLoginAndDeletedFalse(@Param(value = "login") String login);

    User findUsersById(Long id);

    User findByEmail(String email);

    User findByChangePasswordToken(String token);
}
