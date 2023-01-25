package model;

import java.util.Date;

public class NoteMapper {

    public String map(Note note) {
        return String.format("%s,%s,%s,%s\n", note.getId(), note.getTitle(), note.getText(), note.getDate());

    }

    public Note map(String line) {
        String[] lines = line.split(",");
        return new Note(lines[0], lines[1], lines[2], new Date());
    }
}