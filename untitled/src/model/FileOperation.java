package model;

import java.util.List;

public interface FileOperation {
    List<String> readAll();

    void saveAll(List<String> lines);

}
