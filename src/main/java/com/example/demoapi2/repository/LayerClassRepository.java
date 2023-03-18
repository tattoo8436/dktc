package com.example.demoapi2.repository;

import com.example.demoapi2.model.Account;
import com.example.demoapi2.model.LayerClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayerClassRepository extends JpaRepository<LayerClass, Long> {
    @Query("SELECT l FROM LayerClass l WHERE l.subject.id = :subjectId")
    List<LayerClass> getBySubject(@Param("subjectId") long subjectId);
}
