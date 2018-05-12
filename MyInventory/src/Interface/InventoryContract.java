package Interface;

import DataBase.InventorySystem;

public interface InventoryContract {
    InventorySystem ViewInventoryItem(int uid) throws InvalidUserException;
    boolean AddInventoryItem(InventorySystem inv_item) throws InvalidUserException;
    boolean DeleteInventoryItem(int uid) throws  InvalidUserException;
    boolean EditInventoryItem(int replaceme, InventorySystem replacewith) throws InvalidUserException;
    InventorySystem ReloadInventoryItem(int uid) throws InvalidUserException;
}
