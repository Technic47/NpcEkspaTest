package com.example.npc_ekspa;

//Задача такая "Составить эффективный алгоритм":
//Есть 5 полянок. На каждой полянке огород с морковкой.
// На первой полянке морковь массой в 1 кг,
// на второй полянке - морковь массой в 2 кг,
// на третьей полянке - морковь массой в 3 кг,
// на 4 полянке - морковь массой в 4 кг,
// на пятой полянке - морковь массой в 5 кг.
// Зайцу необходимо за 10 ходок унести максимальное количество моркови
// с полянок за 1 день, причем заяц за раз может взять только 5 кг моркови.
// Необходимо составить эффективный алгоритм, который самостоятельно
// должен найти решение на java. Преимуществом будет не только составить алгоритм,
// но программный код, который можно будет проверить.

public class NpcEkspaApplication {
    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static final int ATTEMPTS = 10;
    private static final int LIMIT = 5;

    public static void main(String[] args) {
        int[][] array = prepareArray();
        int result = calculate(array);
    }

    private static int[][] prepareArray() {
        int[][] array = new int[ROWS][COLS];
        for (int k = 0; k < array.length; k++) {
            for (int l = 0; l < array[0].length; l++) {
                array[k][l] = k + 1;
            }
        }
        return array;
    }

    private static int calculate(int[][] array) {
        int result = 0;
        for (int i = 1; i <= ATTEMPTS; i++) {
            int[] initialPosition = getValuePosition(array, 0);
            if (initialPosition == null) {
                break;
            }
            int valueToAdd = takeValueFromArray(array, initialPosition);
            while (valueToAdd < LIMIT) {
                int[] nextPosition = getValuePosition(array, valueToAdd);
                if (nextPosition == null) {
                    break;
                }
                int nextValue = takeValueFromArray(array, nextPosition);
                valueToAdd += nextValue;
            }
            result += valueToAdd;
        }
        return result;
    }

    private static int[] getValuePosition(int[][] array, int currentValue) {
        int[] cell = null;
        for (int i = array.length - 1; i >= 0; i--) {
            if (cell != null) {
                break;
            }
            for (int j = 0; j < array[0].length; j++) {
                int cellValue = array[i][j];
                if (i + 1 + currentValue <= LIMIT) {
                    if (cellValue != 0 && ((cellValue + currentValue) <= LIMIT)) {
                        cell = new int[]{i, j};
                        break;
                    }
                } else break;
            }
        }
        return cell;
    }

    private static int takeValueFromArray(int[][] array, int[] position) {
        int i = position[0];
        int j = position[1];
        int value = array[i][j];
        array[i][j] = 0;
        return value;
    }
}
