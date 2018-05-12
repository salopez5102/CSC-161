package DataBase;

import Interface.InvalidUserException;
import Interface.InventoryContract;
import Items.Roles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDatabase implements InventoryContract {

    DatabaseConnection conn = new DatabaseConnection();
    private Connection _conn;
    private Roles userRole = null;


    @Override
    public InventorySystem ViewInventoryItem(int uid) throws InvalidUserException {
//        if(userRole == null || !userRole.canView() ) throw new InvalidUserException(this.getClass().getName());
        Object returnme = null;
        try {
            ResultSet results = conn.getConnection().createStatement().executeQuery(
                    "SELECT * FROM Inventory WHERE uid=" + uid + ";"
            );
            while (results.next()) {
                returnme = Class.forName(results.getString("kind")).getDeclaredConstructor().newInstance();
                ((InventorySystem)returnme).setInventoryNumber(results.getInt("uid"));
                ((InventorySystem)returnme).setName(results.getString("name"));
                ((InventorySystem)returnme).setValue(results.getFloat("value"));
                if (returnme instanceof SerialNumber)
                    ((SerialNumber)returnme).setSerialNumber(results.getString("serial_number"));
            }
        } catch (Exception any) {
            any.printStackTrace();
        }
        return ((InventorySystem)returnme);
    }

    @Override
    public boolean AddInventoryItem(InventorySystem inv_item) throws InvalidUserException {
        if (ViewInventoryItem(inv_item.getInventoryNumber())!=null) {
            System.out.println("I'm empty");
            return false;
        }
        if(finduid(inv_item.getInventoryNumber()) && inv_item.getInventoryNumber()<getmaxuid()){//if uid  exist in databas and is less then the max uid increse its number
                inv_item.increaseuid(getmaxuid());
        }else if(finduid(inv_item.getInventoryNumber())==false){}
        System.out.println("UID:" + inv_item.getInventoryNumber() + " Name:" + inv_item.getName() + " Value:" + inv_item.getWorth());
        try {
            conn.getConnection().createStatement().executeUpdate(
                    "INSERT INTO Inventory (uid, kind, name, value, serial_number)" +
                            " VALUES (" + inv_item.getInventoryNumber() +
                            ", '" + inv_item.getClass().getName() +
                            "', '" + inv_item.getName() +
                            "', " + inv_item.getWorth() +
                            (inv_item instanceof SerialNumber ?", '" +((SerialNumber)inv_item).getSerialNumber()+"');":", '0');"));
        } catch (SQLException any) {
            any.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean DeleteInventoryItem(int uid) throws InvalidUserException {
        try {
            conn.getConnection().createStatement().executeUpdate(
                    "DELETE FROM Inventory WHERE uid=" + uid + ";"
            );
        } catch (SQLException any) {
            any.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean EditInventoryItem(int replaceme, InventorySystem replacewith) throws InvalidUserException {
        //if(userRole == null || !userRole.canEdit() ) throw new InvalidUserException(this.getClass().getName());
        InventorySystem replacement = ViewInventoryItem(replaceme).cloneInventory(replacewith);
        DeleteInventoryItem(replaceme);
        AddInventoryItem(replacement);
        return false;
    }

    @Override
    public InventorySystem ReloadInventoryItem(int uid) throws InvalidUserException {
        return ReloadInventoryItem(uid);
    }

    public ArrayList<InventorySystem> getAllInventoryOfKind(String look4kind) throws InvalidUserException {
        //if(userRole == null || !userRole.canView() ) throw new InvalidUserException(this.getClass().getName());

        ArrayList<InventorySystem> returnArray = new ArrayList<InventorySystem>();
        Object returnme = null;
        try {
            ResultSet results = conn.getConnection().createStatement().executeQuery(
                    "SELECT * FROM Inventory WHERE kind = '"+look4kind+"';"
            );
            while (results.next()) {
                returnme = Class.forName(results.getString("kind")).getDeclaredConstructor().newInstance();
                ((InventorySystem)returnme).setInventoryNumber(results.getInt("uid"));
                ((InventorySystem)returnme).setName(results.getString("name"));
                ((InventorySystem)returnme).setValue(results.getFloat("value"));
                if (returnme instanceof SerialNumber)
                    ((SerialNumber)returnme).setSerialNumber(results.getString("serial_number"));
                returnArray.add((InventorySystem) returnme);
            }
        } catch (Exception any) {
            any.printStackTrace();
        }
        return returnArray;
    }

    public ArrayList<String> getInventoryKinds() throws InvalidUserException {
        if(userRole == null || !userRole.canView() ){
                    this.getClass().getName();
        }
        ArrayList<String> returnArray = new ArrayList<>();
        Object returnme = null;
        try {
            ResultSet results = conn.getConnection().createStatement().executeQuery(
                    "SELECT DISTINCT kind as kinds from Inventory;"
            );
            while (results.next()) {
                returnArray.add(results.getString("kinds"));
            }
        } catch (Exception any) {
            any.printStackTrace();
        }
        for(int i=0;i<returnArray.size();i++){
            System.out.println(returnArray.get(i));
        }
        return returnArray;
    }
    public boolean finduid(int check){                                      //checks if a uid already exist
        boolean truth=false;
        try {
            ResultSet results = conn.getConnection().createStatement().executeQuery(
              "select *from inventory where uid='"+check+"';"
            );
            while (results.next());
            if(check== results.getInt(1)){
                truth =true;
            }
        }catch (Exception any){

        }
       return truth;
    }
    public int getmaxuid(){                                                             //get the max uid from database
        int maxuid=0;
        try {
            ResultSet result = conn.getConnection().createStatement().executeQuery(
                    "Select Max(uid) from inventory"
            );
            if(result.next()){
                maxuid =result.getInt(1);
            }
        }catch (Exception any){
            any.printStackTrace();
        }
        return  maxuid;
    }
}

