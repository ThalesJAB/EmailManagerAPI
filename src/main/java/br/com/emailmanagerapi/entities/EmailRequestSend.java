package br.com.emailmanagerapi.entities;

public class EmailRequestSend {
    private EmailConfig emailConfig;
    private String to;

    private String displayName;
    private String subject;
    private String text;

    public EmailRequestSend(EmailConfig emailConfig, String to, String displayName, String subject, String text) {
        this.emailConfig = emailConfig;
        this.to = to;
        this.displayName = displayName;
        this.subject = subject;
        this.text = text;
    }

    public EmailRequestSend() {
    }


    public EmailConfig getEmailConfig() {
        return emailConfig;
    }

    public void setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
