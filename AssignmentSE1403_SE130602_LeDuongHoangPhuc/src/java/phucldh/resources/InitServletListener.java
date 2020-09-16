cc/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucldh.resources;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import phucldh.utils.LoadFile;

/**
 * Web application lifecycle listener.
 *
 * @author DELL
 */
public class InitServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        Map<String, String> map = new HashMap<>();
//        String path = request.getServletContext().getRealPath("/WEB-INF/data.txt");
        String path = sc.getRealPath("/WEB-INF/data.txt");

        try {
            LoadFile loadFile = new LoadFile();
            loadFile.load(path);
            map = loadFile.getList();
            sc.setAttribute("FILTER", map);
        } catch (IOException ex) {
            Logger.getLogger(InitServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
