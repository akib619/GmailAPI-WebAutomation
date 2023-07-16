package pages;


import org.openqa.selenium.By;


import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;


public class NewsletterPage extends Form{

    private final IButton subscriptionPlan = getElementFactory().getButton(By.xpath("//label[@for='34422'][normalize-space()='Choose this newsletter']"), "Subscription Plan");

    public NewsletterPage() {
        super(By.className("u-z-index-header"), "Newsletter");
    }

    public void chooseSubscriptionPlan(){
        subscriptionPlan.click();
    }
}
