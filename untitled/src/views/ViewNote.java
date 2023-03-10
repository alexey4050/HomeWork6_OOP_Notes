package views;

import loggers.FileLogger;
import loggers.LogRepositoryFileDecorator;
import model.Note;
import controllers.NoteController;
import model.*;

import java.util.Scanner;

public class ViewNote {

    private NoteController noteController;

    public ViewNote(NoteController noteController) {
        this.noteController = noteController;
    }

    public ViewNote() {

    }

    public void run() {

        Commands com = Commands.NONE;
        String id;
        FileOperation fileOperation;
        Repository repository;

        fileOperation = new FileOperationImpl("notes.txt");
        repository = new LogRepositoryFileDecorator(
                new RepositoryFile(fileOperation),
                new FileLogger(new AppendFileOperationImpl("logi")));
        NoteController noteController = new NoteController(repository);
        //this.noteController = new NoteController(repository);

        while (true) {

            String command = prompt("Введите команду (Create,Read,Update,Delete,List,Exit): ");
            while (!commandValidate(command.toUpperCase())) {
                command = prompt("Введите команду: ");
            }

            com = Commands.valueOf(command.toUpperCase());

            if (com == Commands.EXIT) return;
            try {
                switch (com) {
                    case CREATE:
                        noteController.saveNote(noteCreate());
                        break;

                    case READ:
                        if (noteController.recordsExist()) {
                            id = prompt("Введите id записки: ");
                            try {
                                Note note = noteController.noteRead(id);
                                System.out.println(note);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;

                    case LIST:
                        if (noteController.recordsExist())
                            for (Note item : noteController.readAll()) {
                                System.out.println(item + "\n");
                            }
                        break;

                    case UPDATE:
                        if (noteController.recordsExist()) {
                            id = prompt("Введите id для редактирования: ");
                            noteController.idExists(id);
                            noteController.noteUpdate(id, noteCreate());
                        }
                        break;

                    case DELETE:
                        if (noteController.recordsExist()) {
                            id = prompt("Введите id для удаления: ");
                            noteController.idExists(id);
                            noteController.noteDelete(id);
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }
    }

    private String prompt(String massage) {
        Scanner in = new Scanner(System.in);
        System.out.println(massage);
        return in.nextLine();
    }

    private Note noteCreate() {
        String title = prompt("Заголовок: ");
        String text = prompt("Содержание: ");
        return (new Note(title, text));
    }

    private boolean commandValidate(String command) {
        try {
            Commands.valueOf(command);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Command not found");
            return false;
        }
    }
}






