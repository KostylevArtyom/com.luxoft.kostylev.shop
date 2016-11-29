package database;

import exceptions.DatabaseClassIndexAlreadyExistException;
import exceptions.DatabaseClassIndexNotExistException;
import models.Customer;
import models.Good;
import models.Stock;
import models.Trade;
import models.utils.Constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
    public List<Storable> readAllObjects() {
        List<Storable> storables = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String lineArray[] = line.split(Constants.STORE_SEPARATOR);
                String constructorParameter[][] = {Arrays.copyOfRange(lineArray, 1, lineArray.length)};
                if (!lineArray[0].equals(Trade.class.getName())) {
                    storables.add((Storable)Class.forName(lineArray[0])
                            .getConstructor(String[].class)
                            .newInstance(constructorParameter));
                }
                else {
                    Integer customerId = Integer.valueOf(lineArray[Trade.StorablePositions.CUSTOMER_ID + 1]);
                    Integer goodId = Integer.valueOf(lineArray[Trade.StorablePositions.GOOD_ID + 1]);
                    Customer customer = (Customer)storables
                            .stream()
                            .filter(e -> (e.getClass().equals(Customer.class)) && (e.getId() == customerId))
                            .findAny()
                            .get();
                    Good good = storables
                            .stream()
                            .filter(e -> e.getClass().equals(Stock.class))
                            .map(e -> ((Stock)e).getGood())
                            .filter(e -> e.getId() == goodId)
                            .findAny()
                            .get();
                    storables.add(new Trade(Arrays.copyOfRange(lineArray, 1, lineArray.length), customer, good));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return storables;
    }

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