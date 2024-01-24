package com.bartosznowacki.app.userdetailsservice.notes;

import jakarta.ws.rs.NotAcceptableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class NotesService implements INotesService {
    private final NotesRepository notesRepository;

    public List<Note> getAllNotes(Integer userId) {
        final Optional<List<Note>> notes = notesRepository.findAllByUserId(userId);
        return notes.orElseGet(ArrayList::new);
    }

    public void addNewNote(Integer userId, String value) throws HttpClientErrorException.NotAcceptable {
        Note note = Note.builder()
                .value(value)
                .userId(userId)
                .build();
        notesRepository.save(note);
    }
}
