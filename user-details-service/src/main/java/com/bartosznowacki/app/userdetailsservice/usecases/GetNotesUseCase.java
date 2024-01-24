package com.bartosznowacki.app.userdetailsservice.usecases;

import com.bartosznowacki.app.userdetailsservice.notes.INotesService;
import com.bartosznowacki.app.userdetailsservice.notes.Note;
import com.bartosznowacki.app.userdetailsservice.security.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetNotesUseCase {
    private final INotesService notesService;
    private final IAuthService authService;

    public ResponseEntity<List<Note>> execute() {
        final Integer userId = authService.getLoggedUserId();
        final List<Note> notes = notesService.getAllNotes(userId);
        return ResponseEntity.ok(notes);
    }
}
