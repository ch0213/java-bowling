package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EndFrameCountTest {

    @Test
    @DisplayName("마지막 10번째 프레임은 최대 3개의 값을 가지는 테스트")
    void isMax_test() {
        assertThat(EndFrameCount.of(2).isMax()).isFalse();
        assertThat(EndFrameCount.of(3).isMax()).isTrue();
    }

}