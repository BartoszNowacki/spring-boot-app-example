package com.bartosznowacki.app.userdetailsservice.notes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotesRepository extends JpaRepository<Note, Integer> {
    Optional<List<Note>> findAllByUserId(Integer userId);
}