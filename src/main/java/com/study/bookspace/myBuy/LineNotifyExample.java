package com.study.bookspace.myBuy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sendMessage")
public class LineNotifyExample {

    @PostMapping
    public String sendMessage(@RequestBody String message, RedirectAttributes redirectAttributes) throws IOException {
        String accessToken = "9UoaKJT1HcmhHNl47i5iOGcbEi41yrKr0aKbfDqQIin";

        // LINE Notify API 호출 URL
        String url = "https://notify-api.line.me/api/notify";

        // HTTP 연결 설정
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);

        // 요청 본문에 메시지 추가
        conn.setDoOutput(true);
        conn.getOutputStream().write(message.getBytes());

        // 요청 보내기
        int responseCode = conn.getResponseCode();

        // 응답 읽기
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Body: " + response.toString());

        // 리다이렉트할 URL 설정
        String redirectUrl = "/aGoods/buyManage";

        // 리다이렉트할 URL과 함께 데이터 전달
        redirectAttributes.addAttribute("responseCode", responseCode);
        redirectAttributes.addAttribute("responseBody", response.toString());

        // 리다이렉트할 URL 반환
        return "redirect:" + redirectUrl;
    }

}
