package br.com.desafiojava.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public enum PositionEnum {

    POSITION_0("13"),
    POSITION_1("024"),
    POSITION_2("15"),
    POSITION_3("046"),
    POSITION_4("1357"),
    POSITION_5("248"),
    POSITION_6("37"),
    POSITION_7("468"),
    POSITION_8("57");

    private final String position;

    PositionEnum(String position) {
        this.position = position;
    }

    public static String getPosition(int index) {
        if (index > PositionEnum.values().length)
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        var position = PositionEnum.values()[index];
        return position.getPosition();
    }

    public static List<String> getAllValidIndices(int index, List<String> board) {
        if (board == null)
            throw new IllegalArgumentException("ArrayList cannot be null");

        List<String> indexList = new ArrayList<>();
        var positionValid = PositionEnum.getPosition(index);

        for (var i = 0; i < positionValid.length(); i++) {
            indexList.add(board.get(Integer.parseInt(String.valueOf(positionValid.charAt(i)))));
        }
        indexList.sort(Comparator.naturalOrder());
        return indexList;
    }

    public static boolean isValidMoviment(int index, Integer indexMoviment) {
        return PositionEnum.getPosition(index).contains(indexMoviment.toString());
    }
}
