package mk.ukim.finki.wp.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CoursePage extends AbstractPage{
@FindBy(css = "btn btn-primary")
private List<WebElement> submitButton;

@FindBy(css ="btn btn-danger")
private List<WebElement> deleteButton;

@FindBy(css = "btn btn-warning")
private List<WebElement> editButton;

    @FindBy(css = "btn btn-success")
    private List<WebElement> addCourseButton;
    @FindBy(css = "btn btn-info")
    private List<WebElement> addGradeButton;
    @FindBy(css = "btn btn-dark")
    private List<WebElement> logOutButton;

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public static CoursePage to(WebDriver driver){
        get(driver,"/course");
        return PageFactory.initElements(driver,CoursePage.class);
    }

    public void assertElements(int submitBtnNum,int deleteBtnNum,int editBtnNum,int addCrsBtnNum,int addGrdBtnNum,int lgOutBtnNum){
        Assert.assertEquals("Submit buttons do not match",submitBtnNum,this.getSubmitButton().size());
        Assert.assertEquals("Delete buttons do not match",deleteBtnNum,this.getDeleteButton().size());
        Assert.assertEquals("Edit buttons do not match",editBtnNum,this.getEditButton().size());
        Assert.assertEquals("Add Course buttons do not match",addCrsBtnNum,this.getAddCourseButton().size());
        Assert.assertEquals("Add Grade buttons do not match",addGrdBtnNum,this.getAddGradeButton().size());
        Assert.assertEquals("Log out buttons do not match",lgOutBtnNum,this.getLogOutButton().size());
    }
}
