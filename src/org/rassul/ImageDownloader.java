package org.rassul;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rassul on 6/9/17.
 */
public class ImageDownloader {
    public static void main(String[] args) throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<List<Row>>() {}.getType();
        Gson gson = new Gson();

        List<String> fileNames = new ArrayList<>();


        fileNames.add("SOM.json");
        fileNames.add("Foundation.json");
        fileNames.add("SST.json");
        fileNames.add("SHSS.json");
        fileNames.add("SENG.json");
        fileNames.add("SOB.json");
        fileNames.add("SOE.json");
        fileNames.add("SOPP.json");




        for(String fileName : fileNames){
            JsonReader reader = new JsonReader(new FileReader(fileName));
            List<Row> data = gson.fromJson(reader, REVIEW_TYPE);
            String[] parts = fileName.split(".json");
            String folderName = parts[0];
            int counter = 1;
            int size = data.size();
            for(Row row : data){
                try {
                    System.out.println("Start downloading of " + counter + " out of " + size);
                    download(row.getUserid(),folderName);
                    System.out.println("End downloading of " + counter + " out of " + size);
                    counter++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void download(String id, String folderName) throws IOException {
        URL url = new URL("http://my.nu.edu.kz/.PhoneBookPortlet/UsernameServlet?type=getphoto&uid=" + id);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream("images/" + folderName + "/" + id + ".jpg");
        fos.write(response);
        fos.close();
    }
}
