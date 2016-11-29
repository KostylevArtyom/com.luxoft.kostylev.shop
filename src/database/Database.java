package database;

import exceptions.DatabaseClassIndexAlreadyExistException;
import exceptions.DatabaseClassIndexNotExistException;

import java.util.List;

public interface Database {
    Storable readObject(String objectClass, Integer objectId) throws DatabaseClassIndexNotExistException;
    List<Storable> readAllObjects();
    void writeObject(Storable object) throws DatabaseClassIndexAlreadyExistException;
    void writeObjects(List<Storable> objectList) throws DatabaseClassIndexAlreadyExistException;
    void updateObject(Storable object);
    void updateObjects(List<Storable> objectList);
}