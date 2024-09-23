package ru.nsu.demidov;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void infoDisplayingTest() {
        Card sampleCard = new Card("Two", "Spades", 2);
        assert (Objects.equals(sampleCard.displayInfo(), "Two of Spades(2)"));
    }
}
