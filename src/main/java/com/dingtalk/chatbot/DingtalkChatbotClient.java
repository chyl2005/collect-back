package com.dingtalk.chatbot;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import com.dingtalk.chatbot.message.Message;
import com.mm.common.utils.JsonUtils;

/**
 * Created by dustin on 2017/3/17.
 */
@Service
public class DingtalkChatbotClient {

    HttpClient httpclient = HttpClients.createDefault();

    public SendResult send(String webhook, Message message) throws IOException {

        HttpPost httppost = new HttpPost(webhook);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(message.toJsonString(), "utf-8");
        httppost.setEntity(se);

        SendResult sendResult = null;
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity());
            sendResult = JsonUtils.json2Object(result, SendResult.class);
            Boolean success = sendResult.getErrcode().equals(0) ? true : false;
            sendResult.setSuccess(success);
        }
        return sendResult;
    }

}


