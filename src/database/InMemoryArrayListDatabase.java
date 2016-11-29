package database;

import exceptions.DatabaseClassIndexAlreadyExistException;
import exceptions.DatabaseClassIndexNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryArrayListDatabase implements Database {
    private ArrayList<Storable> storage;

    public InMemoryArrayListDatabase() {
        storage = new ArrayList<>();
    }

    private ArrayList<Storable> filterObjectsByObjectClass(String objectClass) {
        return storage.stream()
                .filter(e -> e.getClassSimpleName().equals(objectClass))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Boolean isObjectExist(String objectClass, Integer objectId) {
        return filterObjectsByObjectClass(objectClass)
                .stream()
                .filter(e -> e.getId() == objectId)
                .findAny()
                .isPresent();
    }

    @Override
    public Storable readObject(String objectClass, Integer objectId) throws DatabaseClassIndexNotExistException {
        Optional<Storable> s = filterObjectsByObjectClass(objectClass)
                .stream()
                .filter(e -> e.getId() == objectId)
                .findAny();
        return s.orElseThrow(DatabaseClassIndexNotExistException::new);
    }

    @Override
    public List<Storable> readAllObjects() {
        return storage;
    }

    @Override
    public void writeObject(Storable object) throws DatabaseClassIndexAlreadyExistException {
        if (isObjectExist(object.getClassSimpleName(), object.getId()))
            throw new DatabaseClassIndexAlreadyExistException();
        storage.add(object);
    }

    @Override
    public void writeObjects(List<Storable> objectList) throws DatabaseClassIndexAlreadyExistException {
        for (Storable storable: objectList)
            writeObject(storable);
    }

    @Override
    public void updateObject(Storable object) {}

    @Override
    public void updateObjects(List<Storable> objectList) {}
}