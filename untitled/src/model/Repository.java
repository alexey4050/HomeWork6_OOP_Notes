package model;

import java.util.List;

public interface Repository {
    List<Note> getAllNotes();
        void createNote (Note note);
        void updateNote (Note note);
        void deleteNote (Note note);
}