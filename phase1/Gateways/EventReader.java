package Gateways;

import UseCases.EventManager;

import java.io.*;

public class EventReader {
    String fileName;

    public EventReader(String fileName){
        this.fileName = fileName;
    }

    public EventManager readFile(){
        try{
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            EventManager eventManager = (EventManager) oi.readObject();

            oi.close();
            fi.close();

            return eventManager;

        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new EventManager(null);
    }

    public void saveFile(EventManager eventManager){
        try{
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(eventManager);
            o.close();
            f.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        }
    }
}
