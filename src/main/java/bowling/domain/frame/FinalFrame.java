package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame extends Frame {

    public FinalFrame() {
        this.scores = new ArrayList<>();
    }

    public FinalFrame(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public boolean isFinished() {
        if (scores.isEmpty()) {
            return false;
        }
        if (scores.size() == 3) {
            return true;
        }
        if (scores.size() == 2 && (scores.get(0) != Score.STRIKE && scores.get(1) != Score.SPARE)) {
            return true;
        }
        return false;
    }

    @Override
    public void addScore(int score) throws Exception {
        if (isFinished()) {
            throw new Exception();
        }
        if (scores.size() == 0) {
            this.scores.add(Score.valueOf(score));
            return;
        }
        if (scores.size() == 1) {
            this.scores.add(Score.valueOf(scores.get(0), score));
            return;
        }
        if (scores.size() == 2) {
            this.scores.add(Score.valueOf(scores.get(1), score));
            return;
        }
    }

    @Override
    public List<Score> getScores() {
        return scores;
    }

    private List<Score> add(Score score) {
        this.scores.add(score);
        return this.scores;
    }
}