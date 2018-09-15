/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerCollectionsDAO;
import dao.CustomerDAOInterface;
import dao.CustomerDatabaseManager;
import dao.DatabaseManager;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;

/**
 *
 * @author bauja773
 */
public class Server extends Jooby{
    
    private DatabaseManager productDao = new DatabaseManager();
    private CustomerDatabaseManager customerDao = new CustomerDatabaseManager();
    
    public Server() {
        port(8059);
        get("/api/products", () -> productDao.getProducts());
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return productDao.search(id);
        });
        use(new Gzon());
        use(new ProductModule(productDao));
        use(new CustomerModule(customerDao));
        use(new AssetModule());
    }  
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("\nStarting Server.");
        
        Server server = new Server();
        
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop server.");
        });
        
        // wait for user to hit the Enter key
        System.in.read();
        System.exit(0);       
    }
    
}
