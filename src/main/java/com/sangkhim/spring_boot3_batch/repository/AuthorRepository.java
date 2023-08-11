package com.sangkhim.spring_boot3_batch.repository;

import com.sangkhim.spring_boot3_batch.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {}
