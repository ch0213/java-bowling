package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변을 삭제하면 답변 기록을 반환하고 검증한다.")
    void deleteByUser() {
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, 1l, A1.getWriter(), LocalDateTime.now());
        assertThat(A1.delete(UserTest.JAVAJIGI).isPresent()).isTrue();
        assertThat(A1.delete(UserTest.JAVAJIGI).get()).isEqualTo(expected);
    }

    @Test
    @DisplayName("자기 자신이 아닌 다른 유저가 답변을 삭제하면 예외를 발생한다.")
    void deleteByUserException(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> A1.delete(A2.getWriter()));
    }

    @Test
    @DisplayName("이미 삭제된 답변은 빈 Optional을 리턴한다.")
    void deletedAnswerReturnEmptyOptional() {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.delete(UserTest.JAVAJIGI)).isNull();
    }
}
