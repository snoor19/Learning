package com.spero.learn;

import com.jcraft.jsch.*;

public class SFTPFileTransfer {

    private static final String REMOTE_HOST = "202.149.24.122";
    private static final String USERNAME = "cpuser492";
    private static final String PASSWORD = "";
    private static final int REMOTE_PORT = 22;
    private static final int SESSION_TIMEOUT = 10000;
    private static final int CHANNEL_TIMEOUT = 5000;

    public static void main(String[] args) {

        String localFile = "D:\\Test\\4492100_20230202000000.tmp";
        String remoteFile = "/data/4492100_20230202000000.tmp";

        Session jschSession = null;

        try {

            JSch jsch = new JSch();
            jsch.setKnownHosts("C:\\Users\\noor.shaik\\Downloads\\known_hosts");
            jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);

            // authenticate using private key
             jsch.addIdentity("C:\\Users\\noor.shaik\\Downloads\\id_rsa");

            // authenticate using password
//            jschSession.setPassword(PASSWORD);

            // 10 seconds session timeout
            jschSession.connect(SESSION_TIMEOUT);

            Channel sftp = jschSession.openChannel("sftp");

            // 5 seconds timeout
            sftp.connect(CHANNEL_TIMEOUT);

            ChannelSftp channelSftp = (ChannelSftp) sftp;

            // transfer file from local to remote server
            channelSftp.put(localFile, remoteFile);

            // download file from remote server to local
            // channelSftp.get(remoteFile, localFile);

            channelSftp.exit();

        } catch (JSchException | SftpException e) {

            e.printStackTrace();

        } finally {
            if (jschSession != null) {
                jschSession.disconnect();
            }
        }

        System.out.println("Done");
    }

}
