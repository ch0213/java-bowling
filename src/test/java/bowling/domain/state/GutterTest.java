package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("거터 상태 테스트")
public class GutterTest {

    State state = new Gutter(1, Scores.empty());

    @DisplayName("다음이 마지막 상태가 될 때")
    @ParameterizedTest
    @MethodSource("getRecordForLast")
    public void nextLast(int pins, Class<State> expectedClass) {
        State nextState = state.record(pins);

        assertThat(nextState).isInstanceOf(expectedClass);
    }

    private static Stream<Arguments> getRecordForLast() {
        return Stream.of(
                Arguments.arguments(10, Spare.class),
                Arguments.arguments(0, Gutter.class),
                Arguments.arguments(3, Ordinary.class)
        );
    }

    @DisplayName("다음 상태")
    @ParameterizedTest
    @MethodSource("getRecord")
    public void next(int pins, Class<State> expectedClass) {
        State state = new Gutter(2, Scores.empty());

        State nextState = state.record(pins);

        assertThat(nextState).isInstanceOf(expectedClass);
    }

    private static Stream<Arguments> getRecord() {
        return Stream.of(
                Arguments.arguments(10, Spare.class),
                Arguments.arguments(0, Gutter.class),
                Arguments.arguments(3, Ordinary.class)
        );
    }

    @DisplayName("점수")
    @Test
    public void score() {
        assertThat(state.getScore()).isEqualTo(Score.gutter());
    }

    @DisplayName("종료 여부")
    @Test
    public void isFinished() {
        assertThat(state.isFinished()).isEqualTo(false);
    }
}