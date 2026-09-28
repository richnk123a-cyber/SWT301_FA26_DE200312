import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import vonguyenkhanh.example.AccountValidator;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountValidatorTest {

    @ParameterizedTest(name = "[{index}] độ dài {0} -> {1}")
    @MethodSource("usernameLengths")
    void isValidUsername_BoundaryLength(int length, boolean expected) {
        assertEquals(
                expected,
                AccountValidator.isValidUsername("a".repeat(length))
        );
    }

    static Stream<Arguments> usernameLengths() {
        return Stream.of(
                Arguments.of(4, false),
                Arguments.of(5, true),
                Arguments.of(6, true),
                Arguments.of(19, true),
                Arguments.of(20, true),
                Arguments.of(21, false)
        );
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @CsvSource(
            delimiter = '|',
            nullValues = "NULL",
            value = {
                    "Secret@123    | alice_01 | true  | hợp lệ",
                    "secret@123    | alice_01 | false | thiếu chữ hoa",
                    "'Secret @123' | alice_01 | false | chứa khoảng trắng",
                    "Xalice_01@1   | alice_01 | false | chứa username",
                    "Xalice_01@1   | NULL     | true  | username null -> bỏ qua"
            }
    )
    void isValidPassword_Partitions(
            String pw,
            String user,
            boolean expected,
            String desc
    ) {
        assertEquals(
                expected,
                AccountValidator.isValidPassword(pw, user)
        );
    }

    @ParameterizedTest(name = "[{index}] sinh {0}, hôm nay {1} -> {2} tuổi")
    @CsvSource({
            "2008-09-28, 2026-09-28, 18",
            "2008-09-29, 2026-09-28, 17",
            "2008-02-29, 2026-02-28, 17",
            "2008-02-29, 2026-03-01, 18"
    })
    void calculateAge_Boundaries(
            LocalDate dob,
            LocalDate today,
            int expected
    ) {
        assertEquals(
                expected,
                AccountValidator.calculateAge(dob, today)
        );
    }
}