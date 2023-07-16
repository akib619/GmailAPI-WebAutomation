package pages;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;

public class EmailFormPage extends Form{

    private final ITextBox txtEmail = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Enter your email']"), "Email");
    private final IButton submit = getElementFactory().getButton(By.xpath("//input[@value='Submit']"), "Submit");

    public EmailFormPage() {
        super(By.id("cpt-newletters-archive-esturgeon-block_624d5b94e56bc"),"Email Form");
    }

    public void enterEmail(String email) {
        txtEmail.type(email);
    }

    public void clickSubmit(){
        submit.click();
    }
    
}
