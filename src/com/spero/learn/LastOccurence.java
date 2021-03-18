package com.spero.learn;

public class LastOccurence {
	
	private static String updateLastSendDate = "update apple_user_outreach set last_send_time = ? where subId IN (?)";

	public static void main(String[] args) {
		
		int lastIndex = updateLastSendDate.lastIndexOf("?");
		StringBuilder builder = new StringBuilder();
		builder.append(updateLastSendDate.substring(0, lastIndex));
		builder.append("noor");
		builder.append(updateLastSendDate.substring(lastIndex + 1));
		System.out.println("Result::"+builder);
	}

}
