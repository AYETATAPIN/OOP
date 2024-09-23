package ru.nsu.demidov;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
class CardTest {
    @Test
    void info_displaying_test() {
        Card sampleCard = new Card("Two", "Spades", 2);
        assert (Objects.equals(sampleCard.displayInfo(), "Two of Spades(2)"));
    }
}
