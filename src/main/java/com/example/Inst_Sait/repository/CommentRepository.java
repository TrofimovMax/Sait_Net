package com.example.Inst_Sait.repository;

import com.example.Inst_Sait.entity.Comment;
import com.example.Inst_Sait.entity.ImegModel;
import com.example.Inst_Sait.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{
    List<Comment> findAllByPost (Post post);

    Comment findByIdAndUserId (Long commentId, Long userId);
}
