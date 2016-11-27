package database;

import java.util.List;

public interface Database {
    List<String> readStringList();
    void writeStringList(List<String> data);
}