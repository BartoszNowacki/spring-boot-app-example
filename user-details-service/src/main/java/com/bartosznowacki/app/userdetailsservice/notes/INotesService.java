package com.bartosznowacki.app.userdetailsservice.notes;

import java.util.List;

public interface INotesService {
    List<Note> getAllNotes(Integer userId);

    void addNewNote(Integer userId, String value);
}
