package com.ybybcsgo.backend.controller;

import com.ybybcsgo.backend.service.GayService;
import com.ybybcsgo.backend.service.MaximService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
public class BaseController
{
    @PostMapping(value = "base", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String Base(@RequestBody String body, HttpServletResponse response)
    {
        JSONObject jsonObject = new JSONObject(body);
        System.out.printf("[BaseController] [Base] Kook WebHook Received Message: %s\n", jsonObject);

        int signalling = jsonObject.getInt("s");
        if (signalling == 0) {
            JSONObject detail = jsonObject.getJSONObject("d");

            String channelType = detail.getString("channel_type");
            // 认证过程
            if (Objects.equals(channelType, "WEBHOOK_CHALLENGE")) {
                String challenge = detail.getString("challenge");
                System.out.printf("[BaseController] [Base] Trying to get challenge key. Result: %s\n", challenge);
                if (challenge != null && !challenge.isEmpty()) {
                    JSONObject responseObject = new JSONObject();
                    responseObject.put("challenge", challenge);

                    System.out.printf("[BaseController] [Base] Return object for challenge. Result: %s\n", responseObject);
                    return responseObject.toString();
                }
            }
            // 频道
            else if (Objects.equals(channelType, "GROUP")) {
                JSONObject extraObject = detail.getJSONObject("extra");

                String targetId = detail.getString("target_id");
                String msgId = detail.getString("msg_id");
                String content = detail.getString("content");

                // Priority 1: Gay Module
                if (content.contains("我是南通"))
                {
                    if (GayService.OnMessageReceive(targetId, msgId, extraObject))
                        return "";
                }

                // Priority 2: Maxim Module
                // 检查是否被@
                if (extraObject.has("mention")) {
                    JSONArray mentionArray = extraObject.getJSONArray("mention");

                    boolean bMentioned = false;
                    int mentionArraySize = mentionArray.length();
                    if (mentionArraySize > 0) {
                        for (int i = 0; i < mentionArraySize; i++) {
                            int mentionTarget = mentionArray.getInt(i);
                            if (mentionTarget == 1817546181)
                            {
                                bMentioned = true;
                                break;
                            }
                        }
                    }

                    if (bMentioned)
                    {
                        if (MaximService.OnMessageReceive(targetId, msgId, extraObject))
                            return "";
                    }
                }

            }
        }

        return "";
    }
}
