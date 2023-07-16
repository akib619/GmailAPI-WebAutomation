package tests;

import org.testng.annotations.Test;

import java.util.List;
import org.testng.Assert;

import pages.ConfirmationPage;
import pages.EmailFormPage;
import pages.HomePage;
import pages.NewsletterPage;
import pages.PreviewPage;
import pages.UnsubscribePage;
import pages.UnsubscriptionMessage;
import utils.ConfigReader;
import utils.Email;
import utils.GetConfirmationMail;
import utils.TestData;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;



public class EuroNewsTest extends BaseTest{
    @Test
    public void testEuroNewsGmailAPI() throws Exception{
        HomePage homePage = new HomePage();
        homePage.clickToSkipTerms();
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Main page is open");
        
        homePage.openNewsletter();

        NewsletterPage newsletterPage = new NewsletterPage();
        Assert.assertTrue(newsletterPage.state().waitForDisplayed(), "Newsletter is open");
        newsletterPage.chooseSubscriptionPlan();

        EmailFormPage emailFormPage = new EmailFormPage();
        Assert.assertTrue(emailFormPage.state().waitForDisplayed(), "An email form has appeared at the bottom of the page");

        Email emailLink = ConfigReader.readJson("src/main/java/resources/testData.json", TestData.class).getMail();
        emailFormPage.enterEmail(emailLink.getEmail());
        emailFormPage.clickSubmit();

        GetConfirmationMail getConfirmationMail = new GetConfirmationMail();
        boolean isEmailExist = GetConfirmationMail.isMailExist("Euronews video newsletter");
        Assert.assertTrue(isEmailExist, "Confirmation email exists");

        String confirmationLink = GetConfirmationMail.getGmailData("subject:Euronews video newsletter");
        browser.goTo(confirmationLink);

        ConfirmationPage confirmationPage = new ConfirmationPage();
        Assert.assertTrue(confirmationPage.state().waitForDisplayed(),"Confirmation");
        confirmationPage.backToMainPage();

        homePage = new HomePage();
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Main page is open");
        homePage.openNewsletter();
       
        PreviewPage previewPage = new PreviewPage();
        previewPage.clickToSeePreviews();
        Assert.assertTrue(previewPage.state().waitForDisplayed(), "A preview of the required plan is opened");
        
        Gmail service = GetConfirmationMail.getService();
        String USER_ID = GetConfirmationMail.USER_ID;
        List<Message> allLettersBeforeUnsubscribe = GetConfirmationMail.listMessagesMatchingQuery(service, USER_ID, "is:unread");

        String unsubscribe = previewPage.unsubscribeNewsletter();
        browser.goTo(unsubscribe);

        UnsubscribePage unsubscribePage = new UnsubscribePage();
        Assert.assertTrue(unsubscribePage.state().waitForDisplayed(), "Unsubscribe page is opened");
        
        emailLink = ConfigReader.readJson("src/main/java/resources/testData.json", TestData.class).getMail();
        unsubscribePage.typeEmail(emailLink.getEmail());
        unsubscribePage.clickUnsubscribe();

        UnsubscriptionMessage unsubscriptionMessage = new UnsubscriptionMessage();
        Assert.assertTrue(unsubscriptionMessage.state().waitForDisplayed(), "A message that the subscription was canceled successfully appears");

        Thread.sleep(30000);
        
        List<Message> allLettersAfterWait = GetConfirmationMail.listMessagesMatchingQuery(service, USER_ID, "is:unread");
        Assert.assertEquals(allLettersAfterWait.size(), allLettersBeforeUnsubscribe.size(), "The letter hasn't arrived");
    }
}

