package bowling.domain.state.end;

import bowling.domain.Pins;
import bowling.domain.frame.Score;
import bowling.domain.state.ThrowingState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    @DisplayName("두 번의 투구가 10개를 쓰러트리지 못했는데 스페어가 생성될 경우")
    @Test
    void 예외_스페어가_아닌_경우() {
        assertThatThrownBy(() -> Spare.create(new Pins(1), new Pins(2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("스페어는 두번의 투구로 %d개 모두 쓰러트려야 합니다.", Pins.MAX_RANGE));
    }

    @DisplayName("한 프레임에서 스페어인데 투구할 때")
    @Test
    void 예외_스페어인데_투구() {
        // given
        ThrowingState state = Spare.create(new Pins(1), new Pins(9));

        // when
        assertThatThrownBy(() -> state.bowl(new Pins(1)))
                // then
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("프레임이 끝난 상태는 투구할 수 없습니다.");
    }

    @Test
    void 투구_끝_여부_확인() {
        ThrowingState state = Spare.create(new Pins(1), new Pins(9));
        assertThat(state.isEnd()).isTrue();
    }

    @Test
    void 심볼_확인() {
        ThrowingState state = Spare.create(new Pins(1), new Pins(9));
        assertThat(state.symbol()).isEqualTo("1|9");
    }

    @DisplayName("스페어 후 다음 1번의 결과(10개)를 합산한 Score를 반환한다.")
    @Test
    void 점수_계산_합산_스페어_후_스트라이크() {
        ThrowingState strike = new Strike();
        Score beforeScore = Score.ofSpare();

        assertThat(strike.calculateAdditionalScore(beforeScore).getScore()).isEqualTo(20);
    }

    @DisplayName("스페어 후 다음 1번의 결과(1개)를 합산한 Score를 반환한다.")
    @Test
    void 점수_계산_합산_스페어_후_미스() {
        ThrowingState miss = Miss.create(new Pins(1), new Pins(8));
        Score beforeScore = Score.ofSpare();

        assertThat(miss.calculateAdditionalScore(beforeScore).getScore()).isEqualTo(11);
    }

    @DisplayName("스페어 후 다음 1번의 결과(스페어 2개)를 합산한 Score를 반환한다.")
    @Test
    void 점수_계산_합산_스페어_후_스페어() {
        ThrowingState miss = Spare.create(new Pins(2), new Pins(8));
        Score beforeScore = Score.ofSpare();

        assertThat(miss.calculateAdditionalScore(beforeScore).getScore()).isEqualTo(12);
    }
}