/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.listener;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import minhtda.utils.DBHelper;

/**
 * Web application lifecycle listener.
 *
 * @author minhd
 */
public class MyContextServletListener implements ServletContextListener {
    

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Deploying ...........");
        ServletContext context = sce.getServletContext();
        String siteMapsFile = context.getInitParameter("SITE_MAP_FILE");
        try {
            Properties siteMaps = DBHelper.getSiteMap(siteMapsFile, context);
            
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            context.log("Context Listener IO " + ex.getMessage());
        }
                
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("RIP");
    }
}
