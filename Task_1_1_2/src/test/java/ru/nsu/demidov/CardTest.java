package ru.nsu.demidov;
import org.junit.jupiter.api.Test;

import java.util.Objects;
class CardTest {
    @Test
    void infoDisplayingTest() {
        Card sampleCard = new Card("Two", "Spades", 2);
        assert (Objects.equals(sampleCard.displayInfo(), "Two of Spades(2)"));
    }
}
