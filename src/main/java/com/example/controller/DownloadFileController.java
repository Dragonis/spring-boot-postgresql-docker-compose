package com.example.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@RestController
public class DownloadFileController {

    private String fileName = "makefile";

    private String pathFromDownload = fileName;

    // http://localhost:8080/download
    @RequestMapping("/download")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {

        FileInputStream inputStream = new FileInputStream(new File(pathFromDownload));

        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream, outputStream);

        outputStream.close();
        inputStream.close();
    }
}
