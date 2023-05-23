package com.example.restservice.repositories;

import com.example.restservice.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountsJPARepository extends JpaRepository<Accounts, Long>{

    @Query(value = "select * from java_class.accounts where user_id = :id", nativeQuery = true)
    Accounts findByUserId(@Param("id") Long id);
    List<Accounts> findByUsernameContains(String username);

    @Query(value = "update java_class.accounts set username = :username, password = :password," +
            "email = :email where user_id = :user_id", nativeQuery = true)
    void updateUserNamePasswordEmail(@Param("username") String username, @Param("password") String password,
                                    @Param("email") String email,  @Param("user_id") Long user_id);

}
