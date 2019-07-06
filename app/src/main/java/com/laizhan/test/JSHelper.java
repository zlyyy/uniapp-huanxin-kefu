package com.laizhan.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;

//工具类，用于js调用java
public class JSHelper {
    private static final String TAG = "JSHelper";
    public static void startActivity(String uname, String upwd, Activity activity, String visitorInfo, String orderInfo) {

        if (ChatClient.getInstance().isLoggedInBefore()) {
            toChatActivity(activity, visitorInfo, orderInfo);
        } else {
            //否则去登录
            login(uname, upwd, activity, visitorInfo, orderInfo);
        }
    }

    private static void toChatActivity(Activity activity, String visitorInfo, String orderInfo){
        // 进入聊天页面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ORDER_INFO, orderInfo);
        Intent intent = new IntentBuilder(activity)
                .setTargetClass(ChatActivity.class)
                .setVisitorInfo(DemoMessageHelper.createVisitorInfo(visitorInfo))
                .setServiceIMNumber(Preferences.getInstance().getCustomerAccount())
//                .setScheduleQueue(DemoMessageHelper.createQueueIdentity(queueName))
                .setTitleName(Constant.TITLE_NAME)
//						.setScheduleAgent(DemoMessageHelper.createAgentIdentity("ceshiok1@qq.com"))
                .setShowUserNick(true)
                .setBundle(bundle)
                .build();
        activity.startActivity(intent);
    }

    private static void login(final String uname, final String upwd, final Activity activity, final String visitorInfo, final String orderInfo) {
        // login huanxin server
        ChatClient.getInstance().login(uname, upwd, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "demo login success!");
                toChatActivity(activity, visitorInfo, orderInfo);
            }

            @Override
            public void onError(int code, String error) {
                Log.e(TAG, "login fail,code:" + code + ",error:" + error);
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(activity,
                                activity.getResources().getString(R.string.is_contact_customer_failure_seconed),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }
}
