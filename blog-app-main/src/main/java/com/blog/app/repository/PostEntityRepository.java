package com.blog.app.repository;

import com.blog.app.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostEntityRepository extends JpaRepository<PostEntity, Integer> {

    @Query(value = "SELECT p FROM PostEntity p WHERE p.id = ?1")
    PostEntity findById(Long id);

    @Modifying
    @Query(value = "DELETE FROM PostEntity p WHERE p.id = ?1")
    void delete(Long id);
}