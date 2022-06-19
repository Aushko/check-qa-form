import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CheckFormTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successfulTest() {
        String fname = "Ivan";
        String lname = "Ivanov";
        String email = "ivan@ivanov.com";
        String phone = "8921333445";
        String gender = "Male";
        String addr = "Current City, bild 1, st.Lenina";

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("[id=firstName]").setValue(fname);
        $("[id=lastName]").setValue(lname);
        $("[id=userEmail]").setValue(email);
        $("#genterWrapper").$(byText((gender))).click();
        $("[id=userNumber]").setValue(phone);
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("[id=currentAddress]").setValue(addr);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("[id=submit]").click();

        $("[id=output]").shouldHave(text(fname), text(lname), text(gender),
              text(email), text(phone), text(addr),
              text("NCR"), text("Delhi"));
    }
}