package com.test.demo.push;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NativeHttpClient;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * https://github.com/jpush/jpush-api-java-client
 * Created on 2017/5/2.
 */
public class JPushDemo {
    private static final String MASTER_SECRET = "2bf52ee46fdeaadb8718fc15";
    private static final String APP_KEY = "d4ee2375846bc30fa51334f5";

    public static void main(String[] args) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        String authCode = ServiceHelper.getBasicAuthorization(APP_KEY, MASTER_SECRET);
        // Here you can use NativeHttpClient or NettyHttpClient.
        NativeHttpClient httpClient = new NativeHttpClient(authCode, null, clientConfig);
        // Call setHttpClient to set httpClient,
        // If you don't invoke this method, default httpClient will use NativeHttpClient.
//        ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, clientConfig);
        jpushClient.getPushClient().setHttpClient(httpClient);
        final PushPayload payload = buildPushObject();

        try {
            PushResult result = jpushClient.sendPush(payload);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jpushClient.close();
        }

    }

    private static PushPayload buildPushObject() {
        JsonObject inbox = new JsonObject();
        inbox.add("line1", new JsonPrimitive("line1 string"));
        inbox.add("line2", new JsonPrimitive("line2 string"));
        inbox.add("contentTitle", new JsonPrimitive("title string"));
        inbox.add("summaryText", new JsonPrimitive("+3 more"));
        Notification notification = Notification.newBuilder()
                .addPlatformNotification(AndroidNotification.newBuilder()
                        .setAlert("alert")
                        .setBigPicPath("path to big picture")
                        .setBigText("long text")
                        .setBuilderId(1)
                        .setCategory("CATEGORY_SOCIAL")
                        .setInbox(inbox)
                        .setStyle(1)
                        .setTitle("Alert test")
                        .setPriority(1)
                        .build())
                .build();
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId("18071adc030dcba91c0"))
                .setNotification(notification)
                .setOptions(Options.sendno())
                .build();
    }
}
