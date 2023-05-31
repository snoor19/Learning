package com.spero.learn;

import java.util.Collection;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.onmobile.prism.util.velocity.VelocityUtils;

//import net.minidev.json.JSONArray;

public class JsonParseRead {

	public void jsonParseMethod() {
		/*
		 * String json =
		 * " {\"file01\": { \"id\": \"0001\"},\"file02\": \"id\": \"0002\"}}";
		 * 
		 * Configuration conf = Configuration.builder().jsonProvider(new
		 * GsonJsonProvider()).build();
		 * 
		 * JsonPath.using(conf).parse(json).read("$.file01");
		 * 
		 * // prints out {"id":"0001"} System.out.println(file01);
		 */
	}
	/*
	 * public static Object getJSONElementValue(String json, String jsonPath){
	 * Object value = ""; try{ value=JsonPath.read(json,jsonPath); //cannot be
	 * string as for integers it throws exception
	 * 
	 * DocumentContext jsonContext = JsonPath.parse(json); Object
	 * jsonpathCreatorName = jsonContext.read(jsonPath);
	 * 
	 * if(value instanceof Collection<?>) { System.out.println("inside Collection");
	 * System.out.println("Is a Array ::"+true); if(((JSONArray)value).size() == 1)
	 * { return ((JSONArray)value).get(0); } } else {
	 * System.out.println("Is a Array ::"+false); }
	 * 
	 * }catch(Exception e){
	 * System.out.println("Exception in fetching value from JSON:{}"+e.getMessage())
	 * ; e.printStackTrace(); } System.out.println("Value to be returned:"+value);
	 * return value; }
	 */

	public static void main(String[] args) {
//		String data = "{\"totalAmountCharged\":0.0,\"transactionId\":\"4p7fdtq1uf9go8qej9l12m14v1\",\"status\":\"denied\",\"statusDate\":\"2021-01-28T00:41:55-03:00\",\"referenceCode\":\"885269214099380386941\"}";
		String data = "{ \"requestId\":\"10184717971629735745\", \"requestTimeStamp\":\"20200927083653\", \"requestParam\":{ \"data\":[ { \"name\":\"OfferCode\", \"value\":\"0010136004501\" }, { \"name\":\"TransactionId\", \"value\":\"10184717971629735745\" }, { \"name\":\"ClientTransactionId\", \"value\":\"92803234\" }, { \"name\":\"Language\", \"value\":\"1\" }, { \"name\":\"SubscriberLifeCycle\", \"value\":\"SUB1\" }, { \"name\":\"SubscriptionStatus\", \"value\":\"A\" }, { \"name\":\"Channel\", \"value\":\"1\" }, { \"name\":\"NextBillingDate\", \"value\":\"2038-07-07 08:36:53\" }, { \"name\":\"Type\", \"value\":\"ACTIVATION\" }, { \"name\":\"OfferName\", \"value\":\"21295_News_updates_10/=per_sms\" }, { \"name\":\"ShortCode\", \"value\":\"1234\" }, { \"name\":\"USER_DATA\", \"value\":\"years\" }, { \"name\":\"Msisdn\", \"value\":\"2549845000000\" } ] }, \"operation\":\"CP_NOTIFICATION\"}";
		String path = "$.requestParam.data[?(@.name == \"SubscriberLifeCycle\")].value";
//		String path = "$.status";
//		System.out.println("return value::" + getJSONElementValue(data, path));
		String data1 = "{\"getSessionKeyResponse\":{\"result\":\"6ffbd0ae-187b-4e92-9dbd-bd87f92fa1e1\"}}";
//		System.out.println("return value::"+getJSONElementValue(data1, "$.getSessionKeyResponse.result"));
		Object value = "";
		try {
			value = JsonPath.read("{\r\n" + 
					"  \"statusCode\": \"0000\",\r\n" + 
					"  \"data\": {\r\n" + 
					"    \"type\": \"Prepaid\",\r\n" + 
					"    \"status\": \"Active\",\r\n" + 
					"    \"startDate\": \"20201019T12:00:00+0000\",\r\n" + 
					"    \"endDate\": \"20240519T12:00:00+0000\",\r\n" + 
					"    \"language\": \"English\",\r\n" + 
					"    \"tariffPlanId\": 1,\r\n" + 
					"    \"tariffPlan\": \"Classic\",\r\n" + 
					"    \"activationStatusFlag\": false,\r\n" + 
					"    \"negativeBarringStatusFlag\": false,\r\n" + 
					"    \"supervisionPeriodWarningActiveFlag\": false,\r\n" + 
					"    \"serviceFeePeriodWarningActiveFlag\": false,\r\n" + 
					"    \"serviceFeePeriodExpiryFlag\": false,\r\n" + 
					"    \"serviceFeePeriodExpiryActiveFlag\": false,\r\n" + 
					"    \"balance\": [\r\n" + 
					"      {\r\n" + 
					"        \"balanceType\": \"Main Balance\",\r\n" + 
					"        \"balanceDetail\": {\r\n" + 
					"          \"type\": \"CURRENCY\",\r\n" + 
					"          \"activeValue\": \"30.00\",\r\n" + 
					"          \"activeUnit\": \"USD\"\r\n" + 
					"        }\r\n" + 
					"      }\r\n" + 
					"    ]\r\n" + 
					"  },\r\n" + 
					"  \"links\": [\r\n" + 
					"    {\r\n" + 
					"      \"rel\": \"self\",\r\n" + 
					"      \"href\": \"https://preprod.api.mtn.com/v2/customers/231880600175/plans\"\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}", "$.data.balance[0].balanceDetail.activeValue"); // cannot be string as for integers it throws exception
		} catch (Exception e) {
			System.out.println("Exception in fetching value from JSON:{}"+ e.getMessage());
		}
		System.out.println("Value to be returned:{}"+ value);
	}
}
