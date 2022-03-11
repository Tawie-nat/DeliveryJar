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

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        $("[data-test-id='city'] input").setValue(DataGenerator.getCity());
        $(".menu-item").shouldBe(Condition.appear, Duration.ofSeconds(5)).click();
        String date = DataGenerator.getData();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue(DataGenerator.getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.getPhone());
        $("span.checkbox__box").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + DataGenerator.getData()));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.getData());
        $(withText("Забронировать")).click();
    }
}