import org.openqa.selenium.By;

import java.util.Random;


public class BaseForm {
    WaitUtil waits;
    BaseElement baseElement;
    private By uniqueFormLocator;
    public String name;

    public BaseForm(By uniqueFormLocator, String name) {
        this.uniqueFormLocator = uniqueFormLocator;
        this.name = name;
    }

    public boolean isPageOpened() {
        return baseElement.getElement().isDisplayed();
    }
    public static void clear(By loc) {
        Browser.driver.findElement(loc).clear();
    }

    public Label getFormLabel(By uniqueFormLocator) {
        //String labelAttribute = Browser.driver.findElement(By.xpath("driver.findElement(By.xpath("//input[@placeholder='choose password']"))/preceding-sibling::label")).getAttribute("for");
        return null;
    }

    // generate a random valid email address
    static String generateRandomEmail() {
        return "test" + generateRandomNumber();
    }
    static String putDomain() {
        return "gmail";
    }
    // generate a random valid password
    static String generateRandomPassword(String email) {
        Random rand = new Random();
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String numerals = "0123456789";
        String cyrillic = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        // generate a random password with at least 10 characters
        StringBuilder password = new StringBuilder();
        while (password.length() < 10) {
            // add a random character from uppercase, lowercase, numerals, or cyrillic
            String source = uppercase + lowercase + numerals + cyrillic + email;
            int index = rand.nextInt(source.length());
            password.append(source.charAt(index));
        }

        // to ensure that the password has at least 1 uppercase letter
        if (!password.toString().matches(".*[A-Z].*")) {
            int index = rand.nextInt(password.length());
            password.setCharAt(index, uppercase.charAt(rand.nextInt(uppercase.length())));
        }

        // to ensure that the password has at least 1 numeral
        if (!password.toString().matches(".*[0-9].*")) {
            int index = rand.nextInt(password.length());
            password.setCharAt(index, numerals.charAt(rand.nextInt(numerals.length())));
        }

        // to ensure that the password has at least 1 letter from the mail adress
        if (!password.toString().matches(".*[" + email + "].*")) {
            int index = rand.nextInt(password.length());
            password.setCharAt(index, email.charAt(rand.nextInt(email.length())));
        }

        // to ensure that the password has at least 1 cyrillic character
        if (!password.toString().matches(".*[" + cyrillic + "].*")) {
            int index = rand.nextInt(password.length());
            password.setCharAt(index, cyrillic.charAt(rand.nextInt(cyrillic.length())));
        }
        return password.toString();
    }

    // generate a random 6-digit number as a string
    private static String generateRandomNumber() {
        Random rand = new Random();
        int number = rand.nextInt(900000) + 100000;
        return String.valueOf(number);
    }

}
