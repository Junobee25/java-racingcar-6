package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import racingcar.domain.CarGenerator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void createCarList_자동차리스트_값_테스트() {
        //given
        final CarGenerator T = new CarGenerator();
        final String case1 = "povi1";
        final String case2 = "povi1,povi2";
        final String case3 = "povi1,povi2,povi3";

        //when
        final LinkedHashMap<String, String> result1 = T.createCarList(case1);
        final LinkedHashMap<String, String> result2 = T.createCarList(case2);
        final LinkedHashMap<String, String> result3 = T.createCarList(case3);

        //then
        assertThat(result1).containsKey("povi1");
        assertThat(result2).containsKey("povi1");
        assertThat(result2).containsKey("povi2");
        assertThat(result3).containsKey("povi1");
        assertThat(result3).containsKey("povi2");
        assertThat(result3).containsKey("povi3");

    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
