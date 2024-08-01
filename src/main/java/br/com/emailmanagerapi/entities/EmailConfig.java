package br.com.emailmanagerapi.entities;

public class EmailConfig {
    private String smtpHost;
    private int smtpPort;
    private String imapHost;
    private int imapPort;
    private String username;
    private String password;
    private boolean smtpStartTlsEnable;
    private boolean smtpSslEnable;

    public EmailConfig() {

    }

    public EmailConfig(String smtpHost, int smtpPort, String imapHost, int imapPort, String username, String password, boolean smtpStartTlsEnable, boolean smtpSslEnable) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.imapHost = imapHost;
        this.imapPort = imapPort;
        this.username = username;
        this.password = password;
        this.smtpStartTlsEnable = smtpStartTlsEnable;
        this.smtpSslEnable = smtpSslEnable;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getImapHost() {
        return imapHost;
    }

    public void setImapHost(String imapHost) {
        this.imapHost = imapHost;
    }

    public int getImapPort() {
        return imapPort;
    }

    public void setImapPort(int imapPort) {
        this.imapPort = imapPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSmtpStartTlsEnable() {
        return smtpStartTlsEnable;
    }

    public void setSmtpStartTlsEnable(boolean smtpStartTlsEnable) {
        this.smtpStartTlsEnable = smtpStartTlsEnable;
    }

    public boolean isSmtpSslEnable() {
        return smtpSslEnable;
    }

    public void setSmtpSslEnable(boolean smtpSslEnable) {
        this.smtpSslEnable = smtpSslEnable;
    }
}
