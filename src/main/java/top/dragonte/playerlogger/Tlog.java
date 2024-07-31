package top.dragonte.playerlogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tlog {
    public Tplayer tplayer;
    public static final Logger LOGGER = LoggerFactory.getLogger("PlayerLogger.Tlog");
    public Tlog(Tplayer tplayer) {
        this.tplayer = tplayer;
    }
    public void log(){
        String fileName=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir="./loginListener/"+"byDate/"+fileName+".log";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        File logs=new File(dir);

        if(!logs.exists()){
            try{
                logs.getParentFile().mkdirs();
                logs.createNewFile();
            }catch(Exception e){
                LOGGER.error("Error: "+e);
            }
        }

        StringBuilder logContent=new StringBuilder();


        logContent.append(tplayer.getName()).append(" ")
                .append(tplayer.stayMinutes()).append(" ")
                .append(sdf.format(tplayer.loginTime)).append(" ")
                .append(sdf.format(tplayer.logoutTime)).append(" ")
                .append(tplayer.getIP()).append(" ")
                .append("\n");
        try{
            FileWriter fw=new FileWriter(dir,true);
            fw.write(logContent.toString());
            fw.close();
        }catch(Exception e){
            LOGGER.error("Error: "+e);
        }
    }
}


