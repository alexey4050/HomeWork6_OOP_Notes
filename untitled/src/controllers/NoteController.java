package controllers;

import model.Note;
import model.Repository;

import java.util.List;

public class NoteController {
    private final Repository repository;

    public NoteController(Repository repository) {
        this.repository = repository;
    }

    public void saveNote(Note note) throws Exception {
        repository.createNote(note);
    }

    public Note noteRead(String id) {
        return  repository.noteRead(id);
    }
    public void exit(){
        repository.exit();
    }

    public List<Note> readAll() {
        return repository.getAllNotes();
    }

    public void noteUpdate(String id, Note updatedNote) throws Exception {
        updatedNote.setId(id);
        repository.updateNote(updatedNote);
    }

    public void noteDelete(String id) {
        Note note = repository.getAllNotes().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        System.out.println(note);
        repository.deleteNote(note);
    }

    public void idExists(String id) throws Exception {
        List<Note> notes = repository.getAllNotes();
        Note note = notes.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
        if (note == null)
            throw new Exception("Запись с id: " + id + " не найдена");

    }

    public boolean recordsExist() {
        List<Note> notes = repository.getAllNotes();
        boolean result = (notes.size() != 0);
        if (!result)
            System.out.println("Список пуст.");
        return result;
    }
}
