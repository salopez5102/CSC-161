import GUI.Login;
import GUI.MainWindow;

public class Main {
    public static void main(String args[]){
        new Login(MainWindow.singleton().getRDB());
        MainWindow.singleton().applyOperationalPermissions();
        /*todo:This project uses the same database as the one created in class*/
    }
}
