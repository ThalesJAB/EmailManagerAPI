package br.com.emailmanagerapi.entities;

public class EmailRequestRead {

    private EmailConfig emailConfig;

    private String folder;


    public EmailRequestRead() {
    }

    public EmailRequestRead(EmailConfig emailConfig, String folder) {
        this.emailConfig = emailConfig;
        this.folder = folder;
    }

    public EmailConfig getEmailConfig() {
        return emailConfig;
    }

    public void setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
