package pages;

import org.openqa.selenium.By;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;

public class PreviewPage extends Form{

    private final ILink seePreviews = getElementFactory().getLink(By.xpath("//a[@href='#the-watch_previews']"), "See Previews");
    private final ILabel frameElement = getElementFactory().getLabel(By.xpath("//div[contains(@id,'previews')][contains(@style,'display')]//iframe"), "Active frame");
    private final ILink unsubscribeLink = getElementFactory().getLink(By.xpath("//a[contains(text(),'clicking here')]"), "Unsubscribe");

    public PreviewPage() {
        super(By.id("the-watch_previews"), "Preview");
    }
    
    
    public void clickToSeePreviews(){
        seePreviews.click();
    }

    public String unsubscribeNewsletter(){
        AqualityServices.getBrowser().getDriver().switchTo().frame(frameElement.getElement());
        String link = unsubscribeLink.getHref();
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
        return link;
    }
}