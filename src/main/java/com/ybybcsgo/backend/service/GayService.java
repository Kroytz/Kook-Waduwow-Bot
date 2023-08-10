package com.ybybcsgo.backend.service;

import com.ybybcsgo.backend.utils.Kook;
import org.json.JSONArray;
import org.json.JSONObject;

public class GayService {

    public static boolean OnMessageReceive(String targetId, String msgId, JSONObject extraObject)
    {
        // 获取用户ID
        JSONObject authorObject = extraObject.getJSONObject("author");
        String authorId = authorObject.getString("id");

        // 回复消息
        JSONObject replyObject = new JSONObject();
        replyObject.put("target_id", targetId);
        replyObject.put("content", "好好好知道你是南通了, 放你进大鸟转转转酒吧！");
        replyObject.put("quote", msgId);
        replyObject.put("temp_target_id", authorId);
        Kook.SendMessageToGroup(replyObject);

        //
        String guildId = extraObject.getString("guild_id");
        BaseCheckGuild(guildId);

        return true;
    }

    public static void BaseCheckGuild(String guildId)
    {
        JSONObject groupObject = new JSONObject(Kook.GetGroupDetail("guild_id="+guildId));

        System.out.printf("[GayService] [CreateChannelIfNotExist] Guild info: %s\n", groupObject);

        JSONObject data = groupObject.getJSONObject("data");

        boolean hasGayChatChannel = false;
        boolean hasGayVoiceChannel = false;

        JSONArray channelsArray = data.getJSONArray("channels");
        int channelCount = channelsArray.length();
        if (channelCount > 0)
        {
            for (int i=0; i<channelCount; i++)
            {
                JSONObject channel = channelsArray.getJSONObject(i);
                String name = channel.getString("name");
                if (name.equals("男同聊天"))
                {
                    hasGayChatChannel = true;
                }

                if (name.equals("男同娇喘"))
                {
                    hasGayVoiceChannel = true;
                }
            }
        }

        if (!hasGayChatChannel || !hasGayVoiceChannel)
        {

        }
    }

}
