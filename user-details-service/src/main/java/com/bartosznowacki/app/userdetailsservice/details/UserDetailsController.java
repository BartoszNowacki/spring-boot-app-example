package com.bartosznowacki.app.userdetailsservice.details;

import com.bartosznowacki.app.userdetailsservice.details.requests.AddNewNoteRequest;
import com.bartosznowacki.app.userdetailsservice.notes.Note;
import com.bartosznowacki.app.userdetailsservice.usecases.AddNewNoteUseCase;
import com.bartosznowacki.app.userdetailsservice.usecases.GetNotesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class UserDetailsController {
    final GetNotesUseCase getNotesUseCase;
    final AddNewNoteUseCase addNewNoteUseCase;

    @GetMapping("/public/test")
    public String getNormal() {
        return "test user";
    }

    @GetMapping("/test/premium")
    @PreAuthorize("hasRole('PREMIUM')")
    public String getPremium() {
        return "premium user";
    }

    @GetMapping("/test/free")
    public String getFree() {
        return "Free user";
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNotes() {
        return getNotesUseCase.execute();
    }

    @PostMapping("/note")
    public ResponseEntity<Void> getNotes(@RequestBody AddNewNoteRequest request) {
        return addNewNoteUseCase.execute(request);
    }
}
