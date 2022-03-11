import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor

public class RegistrationByCardInfo {
    private String city;
    private LocalDate cardExpire;
    private String name;
    private String phone;

    public RegistrationByCardInfo(String city, String fullName, String phoneNumber, LocalDate plusDays) {
    }
}
