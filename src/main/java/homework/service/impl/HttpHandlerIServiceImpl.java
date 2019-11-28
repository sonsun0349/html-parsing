package homework.service.impl;

import homework.service.HttpHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@Service
public class HttpHandlerIServiceImpl implements HttpHandlerService {

    private static final Logger log = LoggerFactory.getLogger(HttpHandlerIServiceImpl.class);

    public String httpHandle(String url) throws NullPointerException {
        StringBuilder sb = new StringBuilder();
        try{
            URL connectUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();
            String responseCode = String.valueOf(conn.getResponseCode());
            if (!responseCode.equals("200"))
                return "";
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str = "";
            while((str = br.readLine())!=null){
                sb.append(str);
            }
            conn.disconnect();
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
