package br.com.emailmanagerapi.entities;

public class EmailMessage {
    private String messageId;
    private String subject;
    private String from;
    private String sentDate;
    private String htmlContent;

    private String messageFormat;
    private boolean isReply;
    private String inReplyTo;


    public EmailMessage() {}


    public EmailMessage(String messageId, String subject, String from, String sentDate, String htmlContent, String messageFormat, boolean isReply, String inReplyTo) {
        this.messageId = messageId;
        this.subject = subject;
        this.from = from;
        this.sentDate = sentDate;
        this.htmlContent = htmlContent;
        this.messageFormat = messageFormat;
        this.isReply = isReply;
        this.inReplyTo = inReplyTo;
    }


    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean isReply) {
        this.isReply = isReply;
    }

    public String getInReplyTo() {
        return inReplyTo;
    }

    public void setInReplyTo(String inReplyTo) {
        this.inReplyTo = inReplyTo;
    }
}
