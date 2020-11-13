package Gateways;

import UseCases.UserManager;

import java.io.*;

public class UserReader {
    String fileName;

    public UserReader(String fileName){
        this.fileName = fileName;
    }

    public UserManager readFile(){
        try{
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            UserManager userManager = (UserManager) oi.readObject();

            oi.close();
            fi.close();

            return userManager;

        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new UserManager(null);
    }

    public void saveFile(UserManager userManager){
        try{
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(userManager);
            o.close();
            f.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        }
    }
}
