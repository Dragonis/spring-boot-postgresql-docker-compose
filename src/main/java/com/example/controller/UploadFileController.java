package com.example.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Uploda file to server
 *
 * METHOD: POST
 * URL: http://localhost:8080/upload
 * BODY: form-data
 *  Key: uploadedFile
 *  Value: (filepath from disk)
 */
@RestController
@RequestMapping(value = "/")
//Max uploaded file size (here it is 20 MB)
@MultipartConfig(fileSizeThreshold = 20971520)
public class UploadFileController {

    private String fileName;

    private String pathToSave;

    @RequestMapping(value = "/upload")
    public String uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) {
        // Get name of uploaded file.
        fileName = uploadedFileRef.getOriginalFilename();

        // Path where the uploaded file will be stored.
        pathToSave = "D:/" + fileName;

        // This buffer will store the data read from 'uploadedFileRef'
        byte[] buffer = new byte[1000];

        // Now create the output file on the server.
        File outputFile = new File(pathToSave);

        FileInputStream reader = null;
        FileOutputStream writer = null;
        int totalBytes = 0;
        try {
            outputFile.createNewFile();

            // Create the input stream to uploaded file to read data from it.
            reader = (FileInputStream) uploadedFileRef.getInputStream();

            // Create writer for 'outputFile' to write data read from
            // 'uploadedFileRef'
            writer = new FileOutputStream(outputFile);

            // Iteratively read data from 'uploadedFileRef' and write to
            // 'outputFile';
            int bytesRead = 0;
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer);
                totalBytes += bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "File uploaded successfully! Total Bytes Read=" + totalBytes;
    }

}