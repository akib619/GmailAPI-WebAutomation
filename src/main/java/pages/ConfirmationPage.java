package pages;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;

public class ConfirmationPage extends Form{

    private final IButton backToSite = getElementFactory().getButton(By.xpath("//span[normalize-space()='Back to the site']"), "Back to the site");
    
    public ConfirmationPage(){
        super(By.xpath("//div[contains(@class,'enw-block-confirmation__text-wrapper')]"), "Confirmation");
    }

    public void backToMainPage(){
        backToSite.click();
    }
}
