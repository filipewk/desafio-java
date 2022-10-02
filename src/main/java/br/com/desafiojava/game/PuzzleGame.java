package br.com.desafiojava.game;

import br.com.desafiojava.enums.PositionEnum;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
public class PuzzleGame {

    private final Scanner scanner = new Scanner(System.in);
    private final List<String> board = Arrays.asList("1", "2", "3", "4", "8", "6", "7", "5", "#");
    private boolean isValidPosition = true;
    private boolean startGame = true;
    private Integer movements = 0;

    private static final String EMPTY_VALUE = "#";

    public void start() {
        while (startGame) {
            showStartingBoard(board);
            showValidNumberToSlide();
            showMovimentMessage();
            moveNumbersWhenValid();
            showBoard(board);
            showChampionsTrophyWhenWin();
        }
    }

    private void showStartingBoard(List<String> board) {
        if (movements == 0 && isValidPosition) {
            Collections.shuffle(board);
            showBoard(board);
        }
    }

    private void showBoard(List<String> board) {
        System.out.println(board.subList(0, 3));
        System.out.println(board.subList(3, 6));
        System.out.println(board.subList(6, 9));
    }

    private String getMoviment() {
        return scanner.next();
    }

    private void showMovimentMessage() {
        System.out.print("Digite o número que deseja movimentar: ");
    }

    private void moveNumbersWhenValid() {
        var moviment = getMoviment();
        var emptyIndex = board.indexOf(EMPTY_VALUE);
        var indexMoviment = board.indexOf(moviment);

        if (!PositionEnum.isValidMoviment(emptyIndex, indexMoviment)) {
            System.out.println("Número escolhido é inválido.");
            isValidPosition = false;
            return;
        }
        board.set(emptyIndex, moviment);
        board.set(indexMoviment, EMPTY_VALUE);
        movements++;
        isValidPosition = true;
    }

    private void showValidNumberToSlide() {
        var emptyIndex = board.indexOf(EMPTY_VALUE);
        var validIndices = PositionEnum.getAllValidIndices(emptyIndex, board);
        var stringBuilder = new StringBuilder();
        validIndices.forEach(i -> stringBuilder.append(" ").append(i).append(","));
        System.out.println("Números que podem ser movimentados:" + stringBuilder.substring(0, stringBuilder.length() - 1));
    }

    private boolean isWinner(List<String> board) {
        var winnerList = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6", "7", "8", "#"));
        return winnerList.equals(board);
    }

    private void showChampionsTrophyWhenWin() {
        if (isWinner(board) && isValidPosition) {
            showWinnerASCII();
            exitGame();
        }
    }

    private void exitGame() {
        startGame = false;
    }

    private void showWinnerASCII() {
        System.out.println(
                "               WINNER!\n" +
                        "             ___________\n" +
                        "            '._==_==_=_.'\n" +
                        "            .-\\:      /-.\n" +
                        "           | (|:.     |) |\n" +
                        "            '-|:.     |-'\n" +
                        "              \\::.    /\n" +
                        "               '::. .'\n" +
                        "                 ) (\n" +
                        "               _.' '._\n" +
                        "            `\"\"\"\"\"\"\"\"\"\"`\n" +
                        "Você venceu o jogo em " + movements + " jogadas."
        );
    }
}
