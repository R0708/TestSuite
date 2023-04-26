package org.example;

import net.bytebuddy.implementation.bytecode.ShiftRight;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSuite {
    public static void clickOnElement(By by){driver.findElement(by).click();}
    public static void typeText(By by,String text){
        driver.findElement(by).sendKeys(text);
    }
    public static String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }
    public static long timestamp(){
        Timestamp timestamp =new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();}

    protected static WebDriver driver;
    static Timestamp timestamp =new Timestamp(System.currentTimeMillis());

    @BeforeMethod
    public static void openBrowser(){
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);}
    @AfterMethod
    public static void closeBrowser(){
        driver.close();
    }

    static String expectedRegistrationCompleteMsg = "Thank you for Register";
    static String expectedReferProductMessage = "Thank you For refer a product";
    static String expectedAbleToVoteMessage = "Can not vote";
    static String expectedCompareProductMessage = "Product can not a compare";
    static String expectedShoppingCartMessage = "Thanks for adding in shopping cart";
    static String expectedCommunityPollVoteMessage = "Thanks for vote communityPoll";
    static String expectedEmailToFriendMassage = "Thank you for your Email";


    @Test
    public static void VerifyUserShouldBeAbleToRegisterSuccessfully(){
        //click on register button
        clickOnElement(By.className("ico-register"));

        //type first name
        typeText(By.id("FirstName"),"jiya");

        //type last name
        typeText(By.id("LastName"),"patel");

        //type email address
        typeText(By.id("Email"),"jiya"+timestamp.getTime()+"@gmail.com");

        //type password
        typeText(By.id("Password"),"1234jiya");

        //type confirm password
        typeText(By.id("ConfirmPassword"),"1234jiya");

        //click on register submit button
        clickOnElement(By.id("register-button"));

        String actualMessage = getTextFromElement(By.className("result"));
        System.out.println("my message : "+actualMessage);

        //for close the browser
        driver.close();
        Assert.assertEquals(actualMessage,expectedRegistrationCompleteMsg," Registration is not working");

    }

    @Test
    public static void VerifyRegisterUserShouldBeAbleToReferAProductToAFriendSuccessfully(){
        clickOnElement(By.className("ico-register"));
        typeText(By.id("FirstName"), "jiya");
        typeText(By.id("LastName"), "patel");
        typeText(By.name("Email"), "jiya@gmail.com");
        typeText(By.id("Password"), "1234jiya");
        typeText(By.id("ConfirmPassword"), "1234jiya");
        // Complete registration
        clickOnElement(By.id("register-button"));
        // Open Login Page
        clickOnElement(By.className("ico-login"));
        // Enter your email address
        typeText(By.id("Email"), "jiya@gmail.com");
        // Enter Password
        typeText(By.id("Password"), "1234jiya");
        // Click Log in button
        clickOnElement(By.xpath("//div[@class=\"buttons\"]//button[@class=\"button-1 login-button\"]"));
        // Choose Apple MacBook Pro 13-inch
        clickOnElement(By.linkText("Apple MacBook Pro 13-inch"));
        // Go to email friend
        clickOnElement(By.className("email-a-friend"));
        // Put friend's email address
        typeText(By.className("friend-email"), "testemail@gmail.com");
        // Type message
        typeText(By.id("PersonalMessage"), "This MacBook is a best");
        // Click on Send email button
        clickOnElement(By.name("send-email"));
        // End of the process Actual message will come
        String actualMessage = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
        System.out.println("My Message:"+actualMessage);
        Assert.assertEquals(actualMessage,expectedReferProductMessage,"Successfully message send");

    }
    @Test
    public static void VerifyRegisterUserShouldBeAbleToVoteSuccessfully(){
        clickOnElement(By.className("ico-register"));
        typeText(By.id("FirstName"), "jiya");
        typeText(By.id("LastName"), "pate");
        typeText(By.name("Email"), "jiya@gmail.com");
        typeText(By.id("Password"), "1234jiya");
        typeText(By.id("ConfirmPassword"), "1234jiya");
        // Complete registration
        clickOnElement(By.id("register-button"));
        // Open Login Page
        clickOnElement(By.className("ico-login"));
        typeText(By.id("Email"), "jiya@gmail.com");
        // Enter Password
        typeText(By.id("Password"), "1234jiya");
        // Click Log in button
        clickOnElement(By.xpath("//div[@class=\"buttons\"]//button[@class=\"button-1 login-button\"]"));
        // Choose polling option
        clickOnElement(By.id("pollanswers-2"));
        // Do Vote
        clickOnElement(By.id("vote-poll-1"));
        String actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
        System.out.println("My Message:"+actualMessage);
        // Expected message
        Assert.assertEquals(actualMessage,expectedAbleToVoteMessage, "Total Votes are wrong");
    }
    @Test
    public static void VerifyUserShouldAbleToCompareProduct(){

        //click on HTC One M8 Android L 5.0 Lollipop
        clickOnElement(By.linkText("HTC One M8 Android L 5.0 Lollipop"));

        //click on add to compare list
        clickOnElement(By.xpath("//div[@class=\"compare-products\"]"));

        //click on gift cards
        clickOnElement(By.linkText("Gift Cards"));

        //click on $25 Virtual Gift Card
        clickOnElement(By.linkText("$25 Virtual Gift Card"));

        //click on add to compare list
        clickOnElement(By.xpath("//div[@class=\"compare-products\"]"));

        //click on product comparison
        clickOnElement(By.linkText("product comparison"));

        //print out both products
        String actualMessage1 = getTextFromElement(By.linkText("HTC One M8 Android L 5.0 Lollipop"));
        System.out.println("First Product:"+actualMessage1);
        String actualMessage2 = getTextFromElement(By.linkText("$25 Virtual Gift Card"));
        System.out.println("Second Product:"+actualMessage2);

        //click on clear list
        clickOnElement(By.className("clear-list"));

        //get text
        String actualMessage3 = getTextFromElement(By.className("You have no items to compare."));
        System.out.println("My Message:"+actualMessage3);

        Assert.assertEquals(actualMessage3,expectedCompareProductMessage, "Can not comparing products");

    }

    @Test
    public static void VerifyUserShouldAbleToAddProductToShoppingCart(){
        //click on electrics
        clickOnElement(By.linkText("Electronics"));

        //click on camera & photo
        clickOnElement(By.linkText("Camera & photo"));

        //click on Leica T Mirrorless Digital Camera
        clickOnElement(By.linkText("Leica T Mirrorless Digital Camer"));

        //click on add to cart
        clickOnElement(By.id("add-to-cart-button-16"));

        //click on Shopping cart
        clickOnElement(By.linkText("shopping cart"));

        //get text
        String actualMessage3 = getTextFromElement(By.className("//*[@id=\"shopping-cart-form\"]/div[1]/table/tbody/tr/td[3]\""));
        System.out.println("My Message:"+actualMessage3);

        Assert.assertEquals(actualMessage3,expectedShoppingCartMessage, "Product is not adding in shopping cart");

    }

    @Test
    public static void VerifyUserShouldAbleToVoteToCommunityPoll(){
        //click on good
        clickOnElement(By.id("pollanswers-2"));

        //click on vote
        clickOnElement(By.id("vote-poll-1"));

     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"poll-vote-error\"]")));

     String actualMessage = getTextFromElement(By.xpath("//div[@class=\"poll-vote-error\"]"));
     System.out.println("my message : "+actualMessage);

        Assert.assertEquals(actualMessage,expectedCommunityPollVoteMessage, "Error message is disappearing");

    }

    @Test
    public static void VerifyUserShouldAbleToSendEmailToFriend(){

        //Click on add to cart button on Apple MacBook pro 13-inch
        driver.findElement(By.linkText("Apple MacBook Pro 13-inch")).click();

        //click on Email a friend
        driver.findElement(By.className("email-a-friend")).click();

        //type friends email address
        driver.findElement(By.className("friend-email")).sendKeys("testemail"+timestamp.getTime()+"@gmail.com");

        //type your email address
        driver.findElement(By.className("your-email")).sendKeys("jiya"+timestamp.getTime()+"@gmail.com");

        //type personal message
        driver.findElement(By.id("PersonalMessage")).sendKeys("hello world");

        //click on send email
        driver.findElement(By.name("send-email")).click();

        String actualMessage = getTextFromElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]/ul[1]"));
        System.out.println("My message : "+actualMessage);

        Assert.assertEquals(actualMessage,expectedEmailToFriendMassage,"Can not sending email");

    }


}





