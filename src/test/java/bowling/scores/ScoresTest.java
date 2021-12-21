package bowling.scores;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.scores.FinalScores;
import bowling.domain.scores.GeneralScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoresTest {

    private static final int STRIKE = 10;
    private static final int[] SPARE = new int[]{3, 7};


    @Test
    @DisplayName("Strike가 있으면 true를 반환한다.")
    void containStrikeTrueTest() {
        assertThat(new GeneralScores(STRIKE).containStrike()).isTrue();

        assertThat(new FinalScores(STRIKE).containStrike()).isTrue();
    }

    @Test
    @DisplayName("Strike가 없으면 false를 반환한다.")
    void containStrikeFalseTest() {
        assertThat(new GeneralScores(5).containStrike()).isFalse();
        assertThat(new GeneralScores(SPARE).containStrike()).isFalse();

        assertThat(new FinalScores(5).containStrike()).isFalse();
        assertThat(new FinalScores(SPARE).containStrike()).isFalse();
    }

    @Test
    @DisplayName("Spare가 있으면 true를 반환한다.")
    void containSpareTrueTest() {
        assertThat(new GeneralScores(SPARE).containSpare()).isTrue();

        assertThat(new FinalScores(SPARE).containSpare()).isTrue();
    }

    @Test
    @DisplayName("Spare가 없으면 false를 반환한다.")
    void containSpareFalseTest() {
        assertThat(new GeneralScores(1, 7).containSpare()).isFalse();

        assertThat(new FinalScores(1, 7).containSpare()).isFalse();
    }

    @Test
    @DisplayName("첫번째 투구가 Gutter가 아니며, 두번째 투구가 Miss라면 true를 반환한다.")
    void isMissTrueTest() {
        assertThat(new GeneralScores(7, 0).isMiss()).isTrue();

        assertThat(new FinalScores(7, 0).isMiss()).isTrue();
    }

    @Test
    @DisplayName("첫번째 투구가 Gutter 아니며, 두번째 투구가 Miss라면 false를 반환한다.")
    void isMissFalseTest() {
        assertThat(new GeneralScores(0).isMiss()).isFalse();
        assertThat(new GeneralScores(0, STRIKE).isMiss()).isFalse();

        assertThat(new FinalScores(0).isMiss()).isFalse();
        assertThat(new FinalScores(0, STRIKE).isMiss()).isFalse();
        assertThat(new FinalScores(0, STRIKE, 0).isMiss()).isFalse();
    }

    @Test
    @DisplayName("모든 투구가 Gutter라면, true를 반환한다.")
    void isGutterTrueTest() {
        assertThat(new GeneralScores(0).isGutter()).isTrue();
        assertThat(new GeneralScores(0, 0).isGutter()).isTrue();

        assertThat(new FinalScores(0).isGutter()).isTrue();
        assertThat(new FinalScores(0, 0).isGutter()).isTrue();
    }

    @Test
    @DisplayName("모든 투구가 Gutter라면, false를 반환한다.")
    void isGutterFalseTest() {
        assertThat(new GeneralScores(1).isGutter()).isFalse();
        assertThat(new GeneralScores(0, 1).isGutter()).isFalse();
        assertThat(new GeneralScores(1, 0).isGutter()).isFalse();

        assertThat(new FinalScores(1).isGutter()).isFalse();
        assertThat(new FinalScores(0, 1).isGutter()).isFalse();
    }


    @Test
    @DisplayName("각 프레임별 점수 합산이 정상 동작한다.")
    void sumScoreTest() {
        assertThat(new GeneralScores(1, 2).sumScore()).isEqualTo(3);

        assertThat(new FinalScores(SPARE).add(5).sumScore()).isEqualTo(15);
    }
}
