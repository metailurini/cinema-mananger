package com.j05promax.cinema.util.common.mail;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.email.EmailPopulatingBuilder;
import org.simplejavamail.email.EmailBuilder;

public class Mail {
    private EmailPopulatingBuilder blank = null;

    public EmailPopulatingBuilder blank() {
        this.blank = EmailBuilder.startingBlank();
        return this.blank;
    }

    public Email toEMail() {
        return this.blank.buildEmail();
    }
}
