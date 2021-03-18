package com.spero.learn;

import com.lambdaworks.redis.RedisAsyncConnection;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.resource.ClientResources;
import com.lambdaworks.redis.resource.DefaultClientResources;

public class RedisClientConn {

private static RedisAsyncConnection<String, Object> conn=null;
	
	private static RedisClient client =null;
	private static ClientResources clientResources=null;
	
	public static void main(String[] args) throws Exception {
		try{
			System.out.println("Creating Redis Connection using configurations mentioned in file :");
			clientResources = new DefaultClientResources.Builder().build();
			RedisURI redisUri = RedisURI.Builder.redis("127.0.0.1",6379).withPassword("Onm0bile").withDatabase(2).build();
			client = RedisClient.create(clientResources, redisUri);
			conn = client.connectAsync(new RedisClientCodec());
			System.out.println("An Asynchronous Redis Connection has been attempted...");
			if(!isConnected()){
				throw new Exception("Unable to establish Redis Connection");
			}
			System.out.println("Connection created successfully...");
			String str = (String) conn.get("spero").get();
			System.out.println("Spero Value in cache::"+str);
		}catch(Exception e){
			System.err.println("Exception in Redis Connection"+e);
			throw new Exception("Exception in establishing Redis Connection",e);
		}
	}
	
	/**
	 * Checks connection status
	 * @return status of connection
	 */
	final static boolean isConnected(){
		return (conn!=null && conn.isOpen());
	}

}
