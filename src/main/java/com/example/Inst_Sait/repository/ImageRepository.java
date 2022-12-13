package com.example.Inst_Sait.repository;

import com.example.Inst_Sait.entity.ImegModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface ImageRepository extends JpaRepository <ImegModel, Long>
{
    Optional<ImegModel> findByUserId (Long id);

    Optional<ImegModel> findByPostId (Long id);
}
