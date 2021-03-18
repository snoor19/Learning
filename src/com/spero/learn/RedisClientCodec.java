package com.spero.learn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.codec.RedisCodec;

public class RedisClientCodec extends RedisCodec<String, Object> {

	private static final Charset charSet = Charset.forName("UTF-8");

	/**
	 * Decode the key output by redis.
	 * 
	 * @param bytes Raw bytes of the key, must not be null.
	 * @return The decoded key, may be null.
	 */
	@Override
	public String decodeKey(ByteBuffer bytes) {
		if (bytes == null) {
			return null;
		}
		byte[] decodedBytes=new byte[bytes.remaining()];
		bytes.get(decodedBytes, 0, decodedBytes.length);
		return new String(decodedBytes, charSet);
	}

	/**
	 * Decode the value output by redis.
	 * 
	 * @param bytes Raw bytes of the value, must not be null.
	 * @return The decoded value, may be null.
	 */
	@Override
	public Object decodeValue(ByteBuffer bytes) {
		if (bytes == null) {
			return null;
		}
		byte[] decodedBytes=new byte[bytes.remaining()];
		bytes.get(decodedBytes, 0, decodedBytes.length);
		ByteArrayInputStream bais = new ByteArrayInputStream(decodedBytes);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException e) {
			System.err.println("Unable to decode...IOException..."+e);
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to decode...ClassNotFoundException"+e);
		} finally {
			try {
				bais.close();
			} catch (IOException e) {
				System.err.println("IOException"+e);
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.err.println("IOException"+e);
				}
			}
		}
		return null;
	}

	/**
	 * Encode the key for output to redis.
	 * @param key the key, may be null.
	 * @return The encoded key, never null.
	 */
	@Override
	public byte[] encodeKey(String key) {
		if (key == null) {
			return new byte[0];
		}
		return key.getBytes(charSet);
	}
	
	
	/**
	 * Encode the value for output to redis.
	 * @param value the value, may be null.
	 * @return The encoded value, never null.
	 */
	@Override
	public byte[] encodeValue(Object value) {
		if (value != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(baos);
				oos.writeObject(value);
				oos.flush();
				return baos.toByteArray();
			} catch (IOException e) {
				System.err.println("IOException..Encoding failed..."+e);
			} finally {
				try {
					baos.close();
				} catch (IOException e) {
					System.err.println("IOException"+e);
				}
				try {
					if (oos != null) {
						oos.close();
					}
				} catch (IOException e) {
					System.err.println("IOException"+e);
				}
			}
		}
		return new byte[0];
	}
}
