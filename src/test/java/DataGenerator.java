import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    public static Faker faker = new Faker(new Locale("ru"));

    public static String getData() {
        Random random = new Random();
        int randomDay = 3 + random.nextInt(362);
        return LocalDate.now().plusDays(randomDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getCity(){
        return faker.address().city();
    }

    public static String getName(){
        return faker.name().fullName();
    }

    public static String getPhone(){
        return faker.numerify("+79#########");
    }


}

