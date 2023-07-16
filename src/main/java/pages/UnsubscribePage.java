package pages;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;

public class UnsubscribePage extends Form{

    private final ITextBox inputEmail = getElementFactory().getTextBox(By.xpath("//input[@id='email']"), "Email");
    private final IButton btnUnsubscribe = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Submit");
    
    public UnsubscribePage() {
        super(By.xpath("//h3[normalize-space()='Newsletter unsubscription']"), "Unsubscribe");
    }

    public void typeEmail(String email) {
        inputEmail.clearAndType(email);
    }

    public void clickUnsubscribe(){
        btnUnsubscribe.click();
    }
    
}
