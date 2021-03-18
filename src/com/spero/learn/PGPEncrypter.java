package com.spero.learn;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;

public class PGPEncrypter {
	
	private String id = "spero";
	private String passwd = "Onm0bile";
	
	private String pubKeyFile = "D:\\Jira\\Requirements\\TEFCVAS_Apple_Music_integration_SM-12121\\Solution\\SecretKey\\Onmobile_public_key.gpg";
	private String privKeyFile = "D:\\Jira\\Requirements\\TEFCVAS_Apple_Music_integration_SM-12121\\2021_2_8\\secring.pgp";

	//create a text file to be encripted, before run the tests
	private String plainTextFile = "D:\\Jira\\Requirements\\TEFCVAS_Apple_Music_integration_SM-12121\\Solution\\EncryptFile\\DataFile.txt";
//	private String cipherTextFile = "D:\\Jira\\Requirements\\TEFCVAS_Apple_Music_integration_SM-12121\\Solution\\EncryptFile\\Monthly_cycle_report.cvs.pgp";
	private String cipherTextFile = "D:\\Jira\\Requirements\\TEFCVAS_Apple_Music_integration_SM-12121\\2021_2_8\\carrierUserOutreach_ES_12f470_02042021_v2_1-1.csv.pgp";
	private String decPlainTextFile = "D:\\Jira\\Requirements\\TEFCVAS_Apple_Music_integration_SM-12121\\2021_2_8\\carrierUserOutreach_ES_12f470_02042021_v2_1-1.txt";

	public static void main(String[] args) {

		PGPEncrypter encrypter = new PGPEncrypter();
		try {
//			encrypter.genKeyPair();
			encrypter.decrypt();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PGPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*
			 * catch (InvalidKeyException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } catch (SignatureException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); } catch (NoSuchAlgorithmException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */ catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void genKeyPair() throws InvalidKeyException, NoSuchProviderException, SignatureException, IOException, PGPException, NoSuchAlgorithmException {

		RSAKeyPairGenerator rkpg = new RSAKeyPairGenerator();

		Security.addProvider(new BouncyCastleProvider());

		KeyPairGenerator    kpg = KeyPairGenerator.getInstance("RSA", "BC");

		kpg.initialize(2048);

		KeyPair                    kp = kpg.generateKeyPair();

		FileOutputStream    out1 = new FileOutputStream(privKeyFile);
		FileOutputStream    out2 = new FileOutputStream(pubKeyFile);

		rkpg.exportKeyPair(out1, out2, kp.getPublic(), kp.getPrivate(), id, passwd.toCharArray(), false);


	}

	
	public void encrypt() throws NoSuchProviderException, IOException, PGPException{
		FileInputStream pubKeyIs = new FileInputStream(pubKeyFile);
		FileOutputStream cipheredFileIs = new FileOutputStream(cipherTextFile);
		PgpHelper.getInstance().encryptFile(cipheredFileIs, plainTextFile, PgpHelper.getInstance().readPublicKey(pubKeyIs), false, true);
		cipheredFileIs.close();
		pubKeyIs.close();
	}
	
	public void decrypt() throws Exception{

		FileInputStream cipheredFileIs = new FileInputStream(cipherTextFile);
		FileInputStream privKeyIn = new FileInputStream(privKeyFile);
		FileOutputStream plainTextFileIs = new FileOutputStream(decPlainTextFile);
		PgpHelper.getInstance().decryptFile(cipheredFileIs, plainTextFileIs, privKeyIn, passwd.toCharArray());
		cipheredFileIs.close();
		plainTextFileIs.close();
		privKeyIn.close();
	}
}
