import javax.swing.UIManager;

/*
 * Class with main() method, which runs application.
 */

/**
 * @version 2.0
 * @author Stanislaw Jakub Klimaszewski
 */
public class Application {
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch (Exception e){
            // do nothing
	}
        new MainWindow();
    }
}
