package com.j05promax.cinema.util.common.mail;

import org.simplejavamail.api.email.EmailPopulatingBuilder;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;

public class GmailEngine extends EmailEngine {
    private Mailer coreMailer;
    private final String sourceEmail;
    private final String serverMail = "smtp.gmail.com";
    private final int serverPort = 465;

    public GmailEngine(String username, String password) {
        this.sourceEmail = username;
        this.coreMailer = MailerBuilder
                .withSMTPServer(
                        this.serverMail,
                        this.serverPort,
                        username,
                        password)
                .withTransportStrategy(TransportStrategy.SMTPS)
                .buildMailer();
    }

    @Override
    public void Send(EmailPopulatingBuilder email) {
        this.coreMailer.sendMail(
                email
                        .from(this.sourceName, this.sourceEmail)
                        .buildEmail(),
                this.async);
    }
}
