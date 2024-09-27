package com.spero.learn;


import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class JsonPathRead {
	
	private static final Logger LOG = LogManager.getLogger(JsonParseRead.class.getName());

	public static void main(String[] args) {
		Object value="";
		try{
			value=JsonPath.read("{\r\n" + 
					"	\"ctid\": \"6b1b34e42c8e48489ecd90dd1463c10d\",\r\n" + 
					"	\"refid\": \"\",\r\n" + 
					"	\"version\": \"V2\",\r\n" + 
					"	\"operator\": \"AIS_THAI\",\r\n" + 
					"	\"circle\": \"ALL\",\r\n" + 
					"	\"msisdn\": \"66937244959\",\r\n" + 
					"	\"uid\": \"\",\r\n" + 
					"	\"sessionid\": \"\",\r\n" + 
					"	\"appid\": 1399,\r\n" + 
					"	\"name\": \"AIS_THAI\",\r\n" + 
					"	\"description\": \"AIS_THAI CA\",\r\n" + 
					"	\"APP_PARTNER_ID\": \"ONMOBILE\",\r\n" + 
					"	\"BLACKLIST_PACK_FILTER_ENABLE\": \"true\",\r\n" + 
					"	\"APP_MANAGE_CREDITS\": \"false\",\r\n" + 
					"	\"DEACTIVATE_ON_ACT_FAILURE\": \"true\",\r\n" + 
					"	\"MULTI_TRIAL_APP_ENABLE\": \"true\",\r\n" + 
					"	\"APP_ENABLE_FILTER_PACKS_FOR_OPERATOR\": \"true\" ,\r\n" + 
					"	\"PASS_THROUGH_WAPSUBSCRIBE\": \"ACT_EVENT_REALTIME\",\r\n" + 
					"	\"status\": [],\r\n" + 
					"	\"packs\": [{\r\n" + 
					"		\"packid\": 139902,\r\n" + 
					"		\"name\": \"AISTHAI_39W_CA\",\r\n" + 
					"		\"description\": \"39THB/7Days\",\r\n" + 
					"		\"PACK_CG_PRODUCT_ID\": \"4492100\",\r\n" + 
					"		\"PACK_OTP_SMS_TEXT\": \"$SMS_PASSCODE$ is your PIN. Please use this code to verify your transaction and do not share it with anyone.\",\r\n" + 
					"		\"APP_TO_HIT_URL\": \"http://localhost:8080/griff/thaicvas/wap/callback/view?msisdn=$MSISDN$&action=$ACTION$&status=$STATUS$&amount=$AMOUNT$&appid=$APPID$&packid=$PACKID$&oldmsisdn=$OLDMSISDN$&oldpackid=$OLDPACKID$&mode=$MODE$&ctid=$CTID$&userinfo=$USERINFO$\",\r\n" + 
					"		\"PACK_CG_PRODUCT_PRICE\": \"00.00\",\r\n" + 
					"		\"PACK_PRODUCT_DESCRIPTION\": \"39THB/7Days\",\r\n" + 
					"		\"type\": \"TRY_AND_BUY\",\r\n" + 
					"		\"automatic_renewal\": true,\r\n" + 
					"		\"CG_SERVICE_ID\": \"AISTHAI_39W_CA\",\r\n" + 
					"		\"PACK_CP_CALL_BACK_URL\": \"https://onmopaycoreapi-stg.onmobilespace.com/api/capture/event/$MSISDN$/1399/139902/$ACTION$?opr=AIS_THAI&userInfo=$USERINFO$&ctid=$CTID$&status=$STATUS$\",\r\n" + 
					"		\"PACK_DISPLAY_CUSTOM_PAGE\": \"true\",\r\n" + 
					"		\"billing_details\": {\r\n" + 
					"			\"currency\": \"INR\",\r\n" + 
					"			\"amount\": 0.0,\r\n" + 
					"			\"credits\": [{\r\n" + 
					"				\"type\": \"Text\",\r\n" + 
					"				\"allowed\": 999,\r\n" + 
					"				\"unit\": \"count\"\r\n" + 
					"			}],\r\n" + 
					"			\"period\": {\r\n" + 
					"				\"length\": 7,\r\n" + 
					"				\"unit\": \"day\"\r\n" + 
					"			},\r\n" + 
					"			\"available_until\": \"2033-01-01 00:00:00\",\r\n" + 
					"			\"billing_flow\": \"DIRECT\",\r\n" + 
					"			\"url\": \"\"\r\n" + 
					"		},\r\n" + 
					"		\"external_details\": {}\r\n" + 
					"	}, {\r\n" + 
					"		\"packid\": 139901,\r\n" + 
					"		\"name\": \"AISTHAI_10D_CA\",\r\n" + 
					"		\"description\": \"10THB/Day\",\r\n" + 
					"		\"PACK_CG_PRODUCT_ID\": \"4492101\",\r\n" + 
					"		\"PACK_OTP_SMS_TEXT\": \"$SMS_PASSCODE$ is your PIN . Please use this code to verify your transaction and do not share it with anyone.\",\r\n" + 
					"		\"APP_TO_HIT_URL\": \"http://localhost:8080/griff/thaicvas/wap/callback/view?msisdn=$MSISDN$&action=$ACTION$&status=$STATUS$&amount=$AMOUNT$&appid=$APPID$&packid=$PACKID$&oldmsisdn=$OLDMSISDN$&oldpackid=$OLDPACKID$&mode=$MODE$&ctid=$CTID$&userinfo=$USERINFO$\",\r\n" + 
					"		\"PACK_CG_PRODUCT_PRICE\": \"00.00\",\r\n" + 
					"		\"PACK_PRODUCT_DESCRIPTION\": \"10THB/Day\",\r\n" + 
					"		\"type\": \"TRY_AND_BUY\",\r\n" + 
					"		\"automatic_renewal\": true,\r\n" + 
					"		\"CG_SERVICE_ID\": \"AISTHAI_10D_CA\",\r\n" + 
					"		\"PACK_CP_CALL_BACK_URL\": \"https://onmopaycoreapi-stg.onmobilespace.com/api/capture/event/$MSISDN$/1399/139901/$ACTION$?opr=AIS_THAI&userInfo=$USERINFO$&ctid=$CTID$&status=$STATUS$\",\r\n" + 
					"		\"PACK_DISPLAY_CUSTOM_PAGE\": \"true\",\r\n" + 
					"		\"billing_details\": {\r\n" + 
					"			\"currency\": \"INR\",\r\n" + 
					"			\"amount\": 0.0,\r\n" + 
					"			\"credits\": [{\r\n" + 
					"				\"type\": \"Text\",\r\n" + 
					"				\"allowed\": 999,\r\n" + 
					"				\"unit\": \"count\"\r\n" + 
					"			}],\r\n" + 
					"			\"period\": {\r\n" + 
					"				\"length\": 1,\r\n" + 
					"				\"unit\": \"day\"\r\n" + 
					"			},\r\n" + 
					"			\"available_until\": \"2033-01-01 00:00:00\",\r\n" + 
					"			\"billing_flow\": \"DIRECT\",\r\n" + 
					"			\"url\": \"\"\r\n" + 
					"		},\r\n" + 
					"		\"external_details\": {}\r\n" + 
					"	}]\r\n" + 
					"}","packs"); //cannot be string as for integers it throws exception
			JSONArray array = (JSONArray) value;
			LOG.debug("Value:;{}",value);
			LOG.debug("----------------------------------------------------------------------------------------");
			String srvId="4492101";
			for (int i = 0; i < array.size(); i++) {
				LinkedHashMap<String, String> object = (LinkedHashMap<String, String>) array.get(i);
				if (object.get("PACK_CG_PRODUCT_ID").equals(srvId)) {
					LOG.debug("Name::{}", object.get("name"));
					LOG.debug("Product_Id::{}", object.get("PACK_CG_PRODUCT_ID"));
				}
			}
			
			String jsonValue = value.toString();
			
			String names = JsonPath.parse(jsonValue)
	                .read("$[?(@.PACK_CG_PRODUCT_ID == '4492101')].name", String.class);
			
			LOG.debug("With json condition::{}",names);
			
			/*
			 * JSONArray arr = new JSONArray(value.toString()); for (int i = 0; i <
			 * arr.length(); i++) { // Walk through the Array. JSONObject obj =
			 * arr.getJSONObject(i); JSONArray orderStatus = obj.getJSONArray("orderItems");
			 * System.out.println("orderStatus::"+orderStatus.getJSONObject(0).getString(
			 * "orderStatus"));
			 * System.out.println("accountNumber::"+orderStatus.getJSONObject(0).getString(
			 * "accountNumber")); }
			 */
		}catch(Exception e){
			LOG.debug("Exception in fetching value from JSON::"+e.getMessage());
		}
		
	}

}
