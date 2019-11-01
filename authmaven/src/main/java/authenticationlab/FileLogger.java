/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 *
 * @author osboxes
 */
public class FileLogger {
    private Logger logger;
    private FileHandler fh;
    
    public FileLogger(){    
        try {
            logger = Logger.getLogger("AuthLogger");
            fh = new FileHandler("./LogFile.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
            logger.info("System startup");
        } catch (Exception e){
            e.printStackTrace();
        }   
    }
    
    public void log(String message){
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        logger.info(ts + " " + message);
    }
    
    
}
