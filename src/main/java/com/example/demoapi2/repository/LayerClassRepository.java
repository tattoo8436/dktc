package com.example.demoapi2.repository;

import com.example.demoapi2.model.Account;
import com.example.demoapi2.model.LayerClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LayerClassRepository extends JpaRepository<LayerClass, Long> {
    @Query("SELECT l FROM LayerClass l WHERE l.subject.id = :subjectId")
    List<LayerClass> getBySubject(@Param("subjectId") long subjectId);

    @Query("SELECT l FROM LayerClass l WHERE l.account.id = :accountId")
    List<LayerClass> getByAccount(@Param("accountId") long accountId);
    @Transactional
    @Modifying
    @Query("UPDATE LayerClass l SET l.account.id = NULL, l.remainQuantity = 1 WHERE l.account.id = :accountId")
    void deleteByAccount(@Param("accountId") long accountId);

    @Transactional
    @Modifying
    @Query("UPDATE LayerClass l SET l.account.id = :accountId, l.remainQuantity = 0 WHERE l.id = :id")
    void updateById(@Param("id") long id, @Param("accountId") long accountId);
}
