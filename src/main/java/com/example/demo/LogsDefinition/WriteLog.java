package com.example.demo.LogsDefinition;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.demo.Helpers.UserInfoHelper;
import org.apache.tomcat.jni.Local;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class WriteLog {
    private String filePath;
    private File logFile;
    private String fileName;
    private String fileExtension = ".txt";

    public WriteLog() throws IOException {
        filePath = new File(".").getCanonicalPath() + "//logs";
        if (!logExists()) {
            createLogFile();
        }
    }

    private void createLogFile() {
        this.fileName = "Log-" + LocalDate.now().toString();
        logFile = new File(this.filePath + "//" + fileName + this.fileExtension);
        try {
            if (logFile.createNewFile()) {
                System.out.println("File create successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean logExists() {
        Iterator<File> files = Arrays.stream(new File(this.filePath).listFiles()).iterator();
        while (files.hasNext()) {
            File temp = files.next();
            if (temp.getName().contains(LocalDate.now().toString())) {
                this.logFile = temp;
                this.filePath = temp.getPath();
                return true;
            }
        }
        return false;
    }

    public void writeLog() {
        SystemLog systemLog = new SystemLog.SystemLogBuilder().setLogID(UUID.randomUUID()).setPath(UserInfoHelper.getRequestPath())
                .setUserID(UserInfoHelper.getEmpID()).setSuccessful(true).build();
        String message = systemLog.toString();
        try {
            FileWriter fw = new FileWriter(this.filePath,true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.append("\n");
            bw.append(message+ new Timestamp(System.currentTimeMillis()));
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
