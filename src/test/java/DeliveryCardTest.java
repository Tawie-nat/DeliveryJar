import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {
    private static Faker faker;
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Desktop\\DeliveryCard\\driver\\win\\chromedriver.exe");
        faker = new Faker(new Locale("ru"));
    }


    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");

    }


    @Test
    public void shouldSendFormToCard() {
        $("[data-test-id=city] input").setValue(DataGenerator.getCity());

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date1 = DataGenerator.getDate();
        $("[data-test-id=date] input").setValue(date1);

        $("[data-test-id=name] input").setValue(DataGenerator.getName());

        $("[data-test-id=phone] input").setValue(DataGenerator.getPhone());

        $("[data-test-id=agreement]").click();

        $("[type='button'] [class=button__text]").click();

        $(".notification__content").shouldHave(text("Встреча успешно запланирована на " + date1),Duration.ofSeconds(5));

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date2 = DataGenerator.getDate();
        $("[data-test-id=date] input").setValue(date2);

        $("[type='button'] [class=button__text]").click();

        $("[data-test-id='replan-notification'] [class='notification__content']").shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] [class=button__text]").shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text("Перепланировать")).click();
        $(".notification__content").shouldHave(text("Встреча успешно запланирована на " + date2),Duration.ofSeconds(5));
        $(withText(date2)).shouldBe(appear);


    }
}