
import dao.DatabaseManager;
import gui.MainMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bauja773
 */
public class Administration {
    
    
    
    public static void main(String[] args) {
        
        DatabaseManager db = new DatabaseManager();
        
        MainMenu menu = new MainMenu(db);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}
