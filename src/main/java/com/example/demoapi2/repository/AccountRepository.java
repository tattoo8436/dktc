package com.example.demoapi2.repository;

import com.example.demoapi2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password")
    List<Account> getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
