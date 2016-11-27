package database;

import java.util.ArrayList;
import java.util.List;

public class MockDatabase implements Database {
    @Override
    public List<String> readRowList() {
        return new ArrayList<>();
    }

    @Override
    public void writeObjectList(List data) {}
}