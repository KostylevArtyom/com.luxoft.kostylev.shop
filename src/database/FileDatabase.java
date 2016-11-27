package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileDatabase implements Database {
    private Path filePath;

    public FileDatabase(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    @Override
    public List<String> readStringList() {
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeStringList(List<String> data) {}
}