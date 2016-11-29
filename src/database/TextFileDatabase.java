package database;

import exceptions.DatabaseClassIndexAlreadyExistException;
import exceptions.DatabaseClassIndexNotExistException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TextFileDatabase implements Database {
    private Path filePath;

    public TextFileDatabase(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    public TextFileDatabase(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Storable readObject(String objectClass, Integer objectId) throws DatabaseClassIndexNotExistException {return null;}

    @Override
    public List<Storable> readAllObjects() {return null;}

    @Override
    public void writeObject(Storable object) throws DatabaseClassIndexAlreadyExistException {}

    @Override
    public void writeObjects(List<Storable> objectList) throws DatabaseClassIndexAlreadyExistException {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {
            for (Storable storable: objectList) {
                bufferedWriter.write(storable.toStorableString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateObject(Storable object) {}

    @Override
    public void updateObjects(List<Storable> objectList) {}
}