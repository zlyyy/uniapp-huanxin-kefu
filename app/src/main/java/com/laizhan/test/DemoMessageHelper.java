package com.laizhan.test;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hyphenate.helpdesk.model.AgentIdentityInfo;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.hyphenate.helpdesk.model.OrderInfo;
import com.hyphenate.helpdesk.model.QueueIdentityInfo;
import com.hyphenate.helpdesk.model.VisitorInfo;
import com.hyphenate.helpdesk.model.VisitorTrack;

/**
 * 对轨迹跟踪的消息操作 此类不是必须，只是为了演示和初始化一些数据
 */
public class DemoMessageHelper {

	public static final String IMAGE_URL_1 = "http://o8ugkv090.bkt.clouddn.com/hd_one.png";
	public static final String IMAGE_URL_2 = "http://o8ugkv090.bkt.clouddn.com/hd_two.png";
	public static final String IMAGE_URL_3 = "http://o8ugkv090.bkt.clouddn.com/hd_three.png";
	public static final String IMAGE_URL_4 = "http://o8ugkv090.bkt.clouddn.com/hd_four.png";


	public static VisitorInfo createVisitorInfo(String visitorInfo) {
		if(visitorInfo == null){
			return null;
		}
		JSONObject visitor = JSON.parseObject(visitorInfo);
		VisitorInfo info = ContentFactory.createVisitorInfo(null);
		info.nickName(visitor.getString("userNickname"))
		    .name(visitor.getString("trueName"))
		    .qq(visitor.getString("qq"))
			.phone(visitor.getString("phone"))
		    .companyName(visitor.getString("companyName"))
		    .description(visitor.getString("description"))
		    .email(visitor.getString("email"));
		Log.e("zzzzzzzzzzzzzzzzzzz", info.getName());
		return info;
	}




	public static VisitorTrack createVisitorTrack(Context context, int index) {
		VisitorTrack track = ContentFactory.createVisitorTrack(null);
		switch(index) {
		case 3:
			track.title(context.getString(R.string.test_track_title1))
                 .price("￥5400")
                 .desc( context.getString(R.string.em_example3_text))
                 .imageUrl(IMAGE_URL_3)
                 .itemUrl("http://www.baidu.com");
			break;
		case 4:
			track.title(context.getString(R.string.test_track_title2))
					.price("￥3915000")
					.desc(context.getString(R.string.em_example4_text))
            .     imageUrl(IMAGE_URL_4)
                 .itemUrl("http://www.baidu.com");
			break;
			default:
				break;
		}
		return track;
	}
	
	public static OrderInfo createOrderInfo(Context context, int index) {
		OrderInfo info = ContentFactory.createOrderInfo(null);
		switch(index) {
		case 1:
			info.title(context.getString(R.string.test_order_title1))
			    .orderTitle(String.format("%s：7890",context.getString(R.string.order_number)))
			    .price("￥8000")
			    .desc(context.getString(R.string.em_example1_text))
			    .imageUrl(IMAGE_URL_1)
			    .itemUrl("http://www.baidu.com");
			break;
		case 2:
			info.title(context.getString(R.string.test_order_title2))
				.orderTitle(String.format("%s：7890",context.getString(R.string.order_number)))
		        .price("￥158000")
		        .desc(context.getString(R.string.em_example2_text))
		        .imageUrl(IMAGE_URL_2)
		        .itemUrl("http://www.baidu.com");
			break;
			default:
				break;
		}
		return info;
		
	}

	public static OrderInfo createOrderInfo(Context context, String orderInfo) {
		JSONObject order = JSON.parseObject(orderInfo);
		OrderInfo info = ContentFactory.createOrderInfo(null);
		info.title(order.getString("title"))
				.orderTitle(order.getString("order_title"))
				.price(order.getString("price"))
				.desc(order.getString("desc"))
				.imageUrl(order.getString("img_url"))
				.itemUrl(order.getString("item_url"));
		return info;
	}
	
	public static AgentIdentityInfo createAgentIdentity(String agentName) {
		if (TextUtils.isEmpty(agentName)){
			return null;
		}
		AgentIdentityInfo info = ContentFactory.createAgentIdentityInfo(null);
		info.agentName(agentName);
		return info;
	}
	
	public static QueueIdentityInfo createQueueIdentity(String queueName) {
		if (TextUtils.isEmpty(queueName)){
			return null;
		}
		QueueIdentityInfo info = ContentFactory.createQueueIdentityInfo(null);
		info.queueName(queueName);
		return info;
	}

}
