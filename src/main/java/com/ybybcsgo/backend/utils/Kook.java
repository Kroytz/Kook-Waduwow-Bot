package com.ybybcsgo.backend.utils;

import org.json.JSONObject;

import java.io.IOException;

import static com.ybybcsgo.backend.utils.Constants.REQUEST_URL_BASE;

public class Kook {

    public static String GetGroupDetail(String message)
    {
        String apiUrl = REQUEST_URL_BASE + "/api/v3/guild/view";

        try {
            return Http.getHtmlByGet(apiUrl, message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String SendMessageToGroup(JSONObject message)
    {
        return SendMessageToGroup(message.toString());
    }

    public static String SendMessageToGroup(String message)
    {
        String apiUrl = REQUEST_URL_BASE + "/api/v3/message/create";

        try {
            return Http.getHtmlByPost(apiUrl, message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
