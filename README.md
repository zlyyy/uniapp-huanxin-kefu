# 介绍
uni-app接入android原生环信客服，支持文字、语音、表情、拍照、图片、视频、文件，进入客服页面时可设置访客信息、发送订单或者轨迹消息，基于环信客服访客端demo集成。
# 集成步骤
## android离线打包环境端
   - ### build.gradle 配置
     - 添加客服云SDK及其他依赖 
        ```
        /*环信 start*/
        //环信客服SDK
        implementation 'com.hyphenate:kefu-easeui-android:latest.release'
        //EaseUI中 头像获取用到了glide，请添加glide库
        implementation 'com.github.bumptech.glide:glide:4.9.0'
        //EaseUI中，fragment用到了android-support-v4包
        //其他版本也可以
        implementation 'com.android.support:appcompat-v7:28.0.0'
        implementation 'com.android.support:recyclerview-v7:28.0.0'
        implementation 'com.android.support:support-v4:28.0.0'
        /*环信 end*/
        ``` 
        *注意：这里的support-v4，appcompat，recyclerview版本需要和compileSdkVersion统一*

     - 增加jniLibs的配置 
        ```
        sourceSets{
            main(){
                jniLibs.srcDirs=['libs']
            }
        }
        ```

   - ### 文件及资源拷贝
     - 拷贝如下java文件到对应目录下，其中com.laizhan.test是你应用的包名  
       ![http://docs.easemob.com/cs/start](https://raw.githubusercontent.com/zlyyy/uniapp-huanxin-kefu/master/images/1.jpg)
     - 拷贝em_strings.xml的中英文资源文件到res的values下面
     - 拷贝em_row_sent_order.xml、em_row_sent_track.xml到res的layout下面  
       ![http://docs.easemob.com/cs/start](https://raw.githubusercontent.com/zlyyy/uniapp-huanxin-kefu/master/images/2.jpg)
     - 拷贝不同架构的so库文件到libs下面  
       ![http://docs.easemob.com/cs/start](https://raw.githubusercontent.com/zlyyy/uniapp-huanxin-kefu/master/images/3.jpg)
   - ### AndroidManifest.xml配置
     - 修改AndroidManifest.xml的application标签的android:name属性为android:name=".MyApplication"   
     
       *注意：如果出现manifest merge错误，那么增加如下两行代码：*  
       ![http://docs.easemob.com/cs/start](https://raw.githubusercontent.com/zlyyy/uniapp-huanxin-kefu/master/images/4.jpg)
       ![http://docs.easemob.com/cs/start](https://raw.githubusercontent.com/zlyyy/uniapp-huanxin-kefu/master/images/5.jpg)
     - 在AndroidManifest.xml中注册聊天页面的activity  
        ```
        <!-- 聊天页面 -->
        <activity
            android:name=".ChatActivity"
            android:launchMode="singleTop"
            android:screenOrientation="portrait"
            android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize|navigation"
            android:theme="@style/horizontal_slide"
            android:windowSoftInputMode="adjustResize|stateHidden"/>
        ```
   - ### 环信配置修改 
      修改Constant.java中的DEFAULT_CUSTOMER_APPKEY：appkey获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“AppKey”     
      修改DEFAULT_CUSTOMER_ACCOUNT：IM服务号获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”   
      修改DEFAULT_TENANT_ID：tenantId获取地址：kefu.easemob.com，“管理员模式 > 设置 > 企业信息”页面的“租户ID”  
      修改TITLE_NAME：聊天页面title，比如“阿里云客服”  
        ```
        public static final String DEFAULT_CUSTOMER_APPKEY = "1421190705061167#kefuchannelapp19515";
        public static final String DEFAULT_CUSTOMER_ACCOUNT = "kefuchannelimid_128577";
        public static final String DEFAULT_TENANT_ID = "19515";
        public static final String TITLE_NAME = "阿里云客服";
        ```
   - ### 修改标题栏颜色  
      为了搭配app主色调，强行修改下面的值就可以了  
      ![http://docs.easemob.com/cs/start](https://raw.githubusercontent.com/zlyyy/uniapp-huanxin-kefu/master/images/6.jpg)
## uni-app端启动客服页面的方法
```
//获取当前Activity  
var main = plus.android.runtimeMainActivity();
// 通过5 sdk 插件的invoke方法起调对象方法
var visitorInfo = {
	trueName: 'zly',
	qq: '649760297',
	phone: '13738038888',
	companyName: 'laizhan',
	userNickname: '云',
	description: '这是一个好人',
	email: '649760297@qq.com'	
};
var orderInfo = {
	title: '测试',
	order_title: '订单号：1234567890',
	price: '12.00',
	desc: '这是一个描述',
	img_url: 'http://oss.laizhangame.com/pic/1.jpeg',
	item_url: 'http://www.baidu.com'
};
plus.android.invoke('com.laizhan.test.JSHelper','startActivity', 'ceshi002', '123456', main, JSON.stringify(visitorInfo), JSON.stringify(orderInfo));
```
其中com.laizhan.test是你自己的应用包名，ceshi002是环信用户账号，123456是环信用户密码，visitorInfo是环信的访客信息设置(不发时传null)，orderInfo是进入客服聊天页面后客户端自动发送给客服的订单/轨迹消息（不发时传null）

*注意：请确保在android环境调用此段代码*

## 其他
   - 环信相关账号的申请与配置，请参考环信官网[http://docs.easemob.com/cs/start](http://docs.easemob.com/cs/start)
   - uni-app的ios离线打包环境对H5支持良好，建议ios先用H5网页方式实现环信客服功能，鄙人有空再补发ios原生版本集成步骤
