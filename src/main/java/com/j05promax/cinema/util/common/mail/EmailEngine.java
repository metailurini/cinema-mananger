package com.j05promax.cinema.util.common.mail;

import org.simplejavamail.api.email.EmailPopulatingBuilder;

public class EmailEngine {
    protected boolean async = true;
    protected String sourceName;

    public EmailEngine setName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    public EmailEngine sendWithAsync(boolean async) {
        this.async = async;
        return this;
    }

    public void Send(EmailPopulatingBuilder email) {
    };
}
