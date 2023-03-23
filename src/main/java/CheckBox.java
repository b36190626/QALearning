import org.openqa.selenium.By;

public class CheckBox extends BaseElement {
    public CheckBox(By locator, String name) {
        super(locator, name);
    }

    public void check() {
        if (isChecked()){
            System.out.println("Already checked!");
        }
        else {click();}
    }
    public void uncheck(){
        if (!isChecked()){
            click();
        }
        else {
            System.out.println("already not selected!");
        }
    }

    public boolean isChecked(){
        return Browser.driver.findElement((uniqueLocator)).isSelected();
    }

}