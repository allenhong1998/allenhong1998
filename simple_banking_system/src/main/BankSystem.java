package main;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BankSystem {

    private Map<String, Accounts> accountsMap;

    public BankSystem(){
        accountsMap = new HashMap<>();
    }
    public Accounts getAccount(String name) {
        return accountsMap.get(name);
    }

    public boolean createAccount(String name, double balance){
        if (accountsMap.containsKey(name)){
            return false;
        }
        accountsMap.put(name, new Accounts(name, balance));
        return true;
    }

//Save and load system state to CSV
    public void saveToCSV(String filename) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename))) {
            writer.writeNext(new String[]{"name", "balance"});
            for (Accounts accounts : accountsMap.values()){
                writer.writeNext(new String[]{accounts.getName(), String.valueOf(accounts.getBalance())});
            }
        }
    }

    public boolean loadFromCSV(String filename){
        try (CSVReader reader = new CSVReader(new FileReader(filename))){
            String[] nextLine;
            //Skip header of CSV
            reader.readNext();
            while ((nextLine = reader.readNext()) != null){
                createAccount(nextLine[0],Double.parseDouble(nextLine[1]));

            }
            return true;
        }  catch (FileNotFoundException e) {
        return false;
        } catch (IOException e) {
            e.printStackTrace();
        return false;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }
}
