package com.myblog.blogapp.repository;

import com.myblog.blogapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    void deleteById(long id);
}
