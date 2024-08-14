package com.study.usedtrade.service;

import com.study.usedtrade.config.RecaptchaConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class RecaptchaService {

    final RecaptchaConfig recaptchaConfig;

    public boolean verifyRecaptcha(String recaptcha) {
        String secretKey = recaptchaConfig.getSecret();
        String url = recaptchaConfig.getUrl();
        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");

            String postParams = "secret=" + secretKey + "&response=" + recaptcha; // 수정: &response=" + recaptcha;
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            // 디버깅용 로그 추가
            System.out.println("reCAPTCHA 응답: " + response.toString());

            return jsonObject.getBoolean("success");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}