package com.ybybcsgo.backend.service;

import com.ybybcsgo.backend.utils.Http;
import com.ybybcsgo.backend.utils.Kook;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

import static com.ybybcsgo.backend.utils.Constants.REQUEST_URL_BASE;
import static com.ybybcsgo.backend.utils.Http.getHtmlByPost;

public class MaximService {

    public static boolean ReplyMaximToGroup(String targetid, String msgid, JSONObject extraObject)
    {
        String maximToSend = GetMaximFromFile();
        if (maximToSend.isEmpty())
            return false;

        JSONObject replyObject = new JSONObject();
        replyObject.put("target_id", targetid);
        replyObject.put("content", maximToSend);
        replyObject.put("quote", msgid);
        replyObject.put("nonce", "nonce");

//        System.out.printf("[MaximService] [ReplyMaximToGroup] Request URL: %s\n", sendMessageAPIUrl);
        System.out.printf("[MaximService] [ReplyMaximToGroup] Body: %s\n", replyObject);

        String response = Kook.SendMessageToGroup(replyObject);

        System.out.print("[MaximService] [ReplyMaximToGroup] >>>>>>>>>>\n");
        System.out.printf("[MaximService] [ReplyMaximToGroup] Response: %s\n", response);

        return true;
    }

    public static String GetMaximFromFile()
    {
        String path = "./maxims.txt";

        try {
            List<String> maximsList = Files.readAllLines(new File(path).toPath());
            String maxim = "";
            if (!maximsList.isEmpty())
            {
                Random random = new Random();
                maxim = maximsList.get(random.nextInt(maximsList.size()));
            }

            return maxim;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
