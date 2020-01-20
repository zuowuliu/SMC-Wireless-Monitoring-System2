package com.ren.filedownload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String originFileName = req.getSession().getServletContext().getRealPath("/") + "WEB-INF/upload/NodeMessage1.txt";
        originFileName = originFileName.replace("\\", "/");
        System.out.println(originFileName);
        String newFileName = "C:\\Users\\Ren\\Desktop\\5.txt";
        File file1 = new File(originFileName);
        File file2 = new File(newFileName);//用于生成新的txt文件
        file2.createNewFile();
        FileInputStream fileInputStream = new FileInputStream(file1);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(newFileName);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        int r;
        while((r=dataInputStream.read())!=-1) {
            dataOutputStream.write(r);
        }
        System.out.println("已下载...");
        resp.sendRedirect("http://localhost:8080/downloadSuccess");
        fileInputStream.close();
        dataInputStream.close();
        fileOutputStream.close();
        dataOutputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("已关闭Servlet..");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("开始下载...");
    }
}
