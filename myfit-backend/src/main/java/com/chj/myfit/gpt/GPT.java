package com.chj.myfit.gpt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class GPT {

    private static String API_KEY = "";
    private static String GPT_URL = "";

    public static void main(String[] args) throws IOException, InterruptedException {
        URL url = new URL(GPT_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Scanner sc = new Scanner(System.in);
        
        String subject = sc.nextLine();
        String allergie = sc.nextLine();
        String pastDiet = sc.nextLine();
        
        sc.close();
        
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        JSONObject data = new JSONObject();
        data.put("model", "gpt-3.5-turbo");
        data.put("temperature", 0.7);
        
        String msg = "아래의 정보에 맞추어 식단을 추천해줘:\r\n"
        		+ "1. 목표: {"+subject+"}\r\n"
        		+ "2. 알레르기 유발 성분: {"+allergie+"}\r\n"
        		+ "3. 직전 식사: {"+pastDiet+"}\r\n"
        		+ "\r\n"
        		+ "이때, 식단은 알레르기 유발 성분이 없도록 하고, 아침, 점심, 저녁 각각 추천하되,"
        		+ "아래 양식에 맞추어 대괄호 안에 메뉴와 열량만 작성해서 대답해"
        		+ "/아침/['메뉴 1', '메뉴 2', '메뉴 3']/열량"
        		+ "/점심/['메뉴 1', '메뉴 2', '메뉴 3']/열량"
        		+ "/저녁/['메뉴 1', '메뉴 2', '메뉴 3']/열량";
        System.out.println(msg);
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", msg);

        JSONArray messages = new JSONArray();
        messages.put(message);

        data.put("messages", messages);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bw.write(data.toString());
        bw.flush();
        bw.close();
        
        Thread.sleep(1000);
        
    	// 응답 결과
    	// {  "id": "chatcmpl-9QWuqJRHW5Q0SkaulzCXHJ67bCzvX",  "object": "chat.completion",  "created": 1716110084,  "model": "gpt-3.5-turbo-0125",  "choices": [    {      "index": 0,      "message": {        "role": "assistant",        "content": "아침: 오트밀 1컵 (166kcal) + 두유 1컵 (122kcal)\n점심: 샐러드 샌드위치 (토마토, 오이, 양상추, 터키베이컨) (230kcal)\n저녁: 그릴 치킨 샐러드 (닭가슴살, 야채, 드레싱) (280kcal)"      },      "logprobs": null,      "finish_reason": "stop"    }  ],  "usage": {    "prompt_tokens": 161,    "completion_tokens": 129,    "total_tokens": 290  },  "system_fingerprint": null}
        
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // String sb = br.readLine();
        String line = null;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        
        System.out.println(sb);
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray choicesArray = jsonObject.getJSONArray("choices");
        JSONObject firstChoice = choicesArray.getJSONObject(0);
        JSONObject messageObject = firstChoice.getJSONObject("message");
        String messageContent = messageObject.getString("content");
        System.out.println(messageContent);
        String[] arr = messageContent.split("/");
        for(int i=0;i<arr.length;i++) {
        	System.out.println(i + arr[i]);        	
        }
    }
}
