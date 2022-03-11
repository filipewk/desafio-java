package br.com.desafiojava.enums;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PositionEnumTest {

    @Test
    public void shouldReturnPositionWhenCallGetPositionSuccess() {
        var validIndex = 1;
        var expectedValue = "024";
        var validPosition = PositionEnum.getPosition(validIndex);
        assertEquals(expectedValue, validPosition);
    }

    @Test
    public void shouldThrowsWhenGetPositionFail() {
        var invalidIndex = 100;
        assertThrows(IndexOutOfBoundsException.class, () -> PositionEnum.getPosition(invalidIndex));
    }

    @Test
    public void shouldReturnListOfValidPostionWhenCallGetAllValidIndicesSuccess() {
        var validIndex = 1;
        var expectedValue = List.of("1", "3", "8");
        var board = new ArrayList<>(List.of("1", "2", "3", "4", "8", "6", "7", "5", "#"));
        var validIndices = PositionEnum.getAllValidIndices(validIndex, board);
        assertEquals(expectedValue, validIndices);
    }

    @Test
    public void shouldThrowsWhenCallGetAllValidIndicesFail() {
        var invalidIndex = 100;
        var board = new ArrayList<>(List.of("1", "2", "3", "4", "8", "6", "7", "5", "#"));
        assertThrows(IndexOutOfBoundsException.class, () -> PositionEnum.getAllValidIndices(invalidIndex, board));
        assertThrows(IllegalArgumentException.class, () -> PositionEnum.getAllValidIndices(invalidIndex, null));
    }

    @Test
    public void shouldReturnTrueWhenCallIsValidMovimentSucess() {
        var validIndex = 1;
        var validIndexMoviment = 2;
        var isValid = PositionEnum.isValidMoviment(validIndex, validIndexMoviment);
        assertTrue(isValid);
    }

    @Test
    public void shouldReturnFalseWhenInvalidIndexMoviment() {
        var validIndex = 1;
        var invalidIndexMoviment = 100;
        var isValid = PositionEnum.isValidMoviment(validIndex, invalidIndexMoviment);
        assertFalse(isValid);
    }

    @Test
    public void shouldThrowsisValidMovimentFail() {
        var invalidIndex = 100;
        var validIndexMoviment = 2;
        assertThrows(IndexOutOfBoundsException.class, () -> PositionEnum.isValidMoviment(invalidIndex, validIndexMoviment));
    }
}
