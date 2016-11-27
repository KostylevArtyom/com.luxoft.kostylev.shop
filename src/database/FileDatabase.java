package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    public List<String> readRowList() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeObjectList(List data) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {
            for (Object unit: data) {
                bufferedWriter.write(unit.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}