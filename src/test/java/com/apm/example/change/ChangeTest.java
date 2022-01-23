package com.apm.example.change;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeTest {

    @Test
    public void testNegativeAmountChange() {
        assertThrows(IllegalArgumentException.class, () -> {new Change().getChange(-1);});
    }

    @Test
    public void testZeroChange() {
        assertResult(new Change().getChange(0), 0);
    }

    @Test
    public void testFiverChange() {
        final int amount = 500;
        assertResult(new Change().getChange(amount), amount);
    }

    @Test
    public void testFiverNotEnoughChange() {
        assertThrows(IllegalStateException.class, () -> {new Change(new int[]{2, 0, 0, 0, 0, 0, 0, 0}).getChange(500);});
    }

    @Test
    public void testTennerChange() {
        final int amount = 1000;
        assertResult(new Change().getChange(amount), amount);
    }

    @Test
    public void testTennerChangePence() {
        final int amount = 1000;
        assertResult(new Change(new int[]{0, 0, 0, 0, 0, 0, 0, 1001}).getChange(amount), amount);
    }

    @Test
    public void testPonyChange() {
        final int amount = 2500;
        assertResult(new Change().getChange(amount), amount);
    }

    @Test
    public void testAllTheThingsChange() {
        final int amount = 391;
        assertResult(new Change().getChange(amount), amount);
    }

    @Test
    public void testAllTheThingsOneOfEachChange() {
        final int amount = 391;
        assertResult(new Change(new int[]{1, 1, 1, 1, 1, 1, 1, 1}).getChange(amount), amount);
    }

    private static void assertResult(int[] result, int amount) {
        int value = 0;
        for (int i = 0; i < Change.COINS.length; i++) {
            value += Change.COINS[i]*result[i];
        }
        assertEquals(amount, value);
    }
}
