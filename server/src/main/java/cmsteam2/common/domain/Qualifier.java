package cmsteam2.common.domain;

public enum Qualifier {
    STRONG_ACCEPT(3),
    ACCEPT(2),
    WEAK_ACCEPT(1),
    BORDERLINE(0),
    WEAK_REJECT(-1),
    REJECT(-2),
    STRONG_REJECT(-3);

    public int weight;

    Qualifier(int weight) {
        this.weight = weight;
    }
}
