package database;

import java.util.ArrayList;
import java.util.List;

public class MockDatabase implements Database {
    @Override
    public List<String> readStringList() {
        return new ArrayList<>();
    }

    @Override
    public void writeStringList(List<String> data) {}
}