package com.bartosznowacki.app.userdetailsservice.usecases;

import com.bartosznowacki.app.userdetailsservice.details.requests.AddNewNoteRequest;
import com.bartosznowacki.app.userdetailsservice.notes.INotesService;
import com.bartosznowacki.app.userdetailsservice.security.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddNewNoteUseCase {
    private final INotesService notesService;
    private final IAuthService authService;

    public ResponseEntity<Void> execute(AddNewNoteRequest request) {
        final Integer userId = authService.getLoggedUserId();
        notesService.addNewNote(userId, request.getNote());
        return ResponseEntity.ok().build();
    }
}
