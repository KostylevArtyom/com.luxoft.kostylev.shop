package database;

import java.util.List;

public interface Database {
    List<String> readRowList();
    void writeObjectList(List data);
}