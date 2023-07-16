package pages;

import org.openqa.selenium.By;

import aquality.selenium.forms.Form;

public class UnsubscriptionMessage extends Form{

    public UnsubscriptionMessage() {
        super(By.xpath("//strong[normalize-space()='You are unsubscribed.']"), "Unsubscription message");
    }
    
}
