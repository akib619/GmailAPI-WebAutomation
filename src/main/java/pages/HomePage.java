package pages;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;

public class HomePage extends Form{
    private final IButton skipTerms = getElementFactory().getButton(By.xpath("//span[@role='button']"), "Continue without agreeing");
    private final IButton newsletter = getElementFactory().getButton(By.xpath("//span[@class='u-margin-start-1'][normalize-space()='Newsletters']"), "Newsletter");
    
    public HomePage() {
        super(By.className("u-show-for-xlarge"), "Home Page");
    }  

    public void clickToSkipTerms(){
        skipTerms.click();
    }
        
    public void openNewsletter(){
        newsletter.click();
    }

}
