package com.spero.learn;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class StringSplit {
	
	private static String getsortedQueryParams(String data) {
		if( data == null || data.isEmpty())
			return null;
		else {
			String[] arr = data.split("&");
			if( arr.length == 1)
				return arr[0];
			Arrays.parallelSort(arr);
//			arr = Arrays.stream(arr).sorted().toArray();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				System.out.println("Array-"+i);
				if( i != arr.length-1)
					sb.append(arr[i]+"&");
				else
					sb.append(arr[i]);
			}
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
//		System.out.println("Value::"+"REG ".substring(0,5));
//		System.out.println("Sorted Data::"+getsortedQueryParams("svc=1001&u=439FEC5AE87457E0CA17ECB244C726D7&outs=1598254504&amount=F0578D7FE4FAF5D94DEBD6CE07DC98DC&partnerparam1=partnervalue1&1=noor&101=moha&2=shaik"));
		
		String[] entry = "data:newTest".split(Pattern.quote(":"));
		if (entry.length == 2) {
			for (int i = 0; i < entry.length; i++) {
				System.out.println(entry[i]);
			}
		} else {
			System.out.println("else::"+entry);
		}
		String srvId = "3";
		
		String sql = "select count(*) from subscriptions where sub_status='A'"+ ((srvId != null&&!srvId.isEmpty())?" and srv_id="+srvId:"");
		System.out.println("Sql Query::"+sql);
		
		List<String> s = new ArrayList<>();
		s.add("one");
		s.add("two");
		s.add("three");

		String step1 = StringUtils.join(s, "','");// Join with ", "
		String step2 = StringUtils.wrap(step1, "'");// Wrap step1 with "
		String result = s.stream().collect(Collectors.joining("','", "'", "'"));

		System.out.println(step2);
		System.out.println("Stream::"+result);
		String path = "/swewer/efsdf/opId_99";
		System.out.println("OpId::"+path.split("opId_")[1]);
		String str = "http://localhost:8080/subscription/CAsmartNotification/MO?X-Source-Addr=12364532432&X-Dest-Addr=93230&_sc=REG 3YK06 omtrx0001&_tid&op=HTI";
		try {
			str = java.net.URLDecoder.decode(str, StandardCharsets.UTF_8.name());
			String[] stat = "REG|3YK06|omtrx0001".split(Pattern.quote("|"));
			System.out.println("Final URL::"+Arrays.toString(stat));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileIdsdATA = "CDR1,CDR2,CDR3,CDR4";
		String dataaa = "clientid=1,clientsecret=jhjhjhbhj,username=noor,password=Onm0bile,scope=global";
		String[] keyVals = dataaa.split(",");
		for (String keyVal : keyVals) {
			System.out.println("KeyVal::" + keyVal);
		}
		List<String> fileIds = Arrays.asList(fileIdsdATA.split(","));
		for (String fileId : fileIds) {
			System.out.println("fileId::"+fileId);
		}
	}
}
