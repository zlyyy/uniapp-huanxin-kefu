package com.laizhan.test;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.UIProvider;

import io.dcloud.application.DCloudApplication;

public class MyApplication extends DCloudApplication {

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.init(this);
        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey(Constant.DEFAULT_CUSTOMER_APPKEY);//必填项，appkey获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“AppKey”
        options.setTenantId(Constant.DEFAULT_TENANT_ID);//必填项，tenantId获取地址：kefu.easemob.com，“管理员模式 > 设置 > 企业信息”页面的“租户ID”

        // Kefu SDK 初始化
        if (!ChatClient.getInstance().init(this, options)){
            return;
        }
        // Kefu EaseUI的初始化
        UIProvider.getInstance().init(this);
        //后面可以设置其他属性

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
