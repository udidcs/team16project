package com.example.team16project.repository.user;

import com.example.team16project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    @Modifying
    @Query("update User set nickname = :nickname where userId = :id")
    void updateNicknameByAdmin(Long id, String nickname);

    @Modifying
    @Query("update User set role = :role where userId = :id")
    void updateRoleByAdmin(Long id, String role);

}
