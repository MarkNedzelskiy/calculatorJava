import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> romanNumbers = new HashMap<>();
        romanNumbers.put("I", 1);
        romanNumbers.put("II", 2);
        romanNumbers.put("III", 3);
        romanNumbers.put("IV", 4);
        romanNumbers.put("V", 5);
        romanNumbers.put("VI", 6);
        romanNumbers.put("VII", 7);
        romanNumbers.put("VIII", 8);
        romanNumbers.put("IX", 9);
        romanNumbers.put("X", 10);


        while (true) {
            showMenu();
            String[] operation = scanner.nextLine().split(" ");
            int programm = romanOrArabic(operation, romanNumbers);

            if (operation[1].equals("+") || operation[1].equals("-") ||
                    operation[1].equals("*") || operation[1].equals("/")) {
                switch (programm) {
                    case 1 :
                        calculateRoman(operation, romanNumbers);
                        break;
                    case 2 :
                        calculateArabic(operation);
                        break;
                }
            } else {
                    throw new IOException();
                }
        }

    }

    /**
     * вызов меню
     */
    static void showMenu(){
        System.out.println("Введите желаемую операцию: ");
    }

    /**
     * определение вида цифр и дальнейшего поведения программы
     * @param strings
     * @param numbers
     * @return
     * @throws IOException
     */
    static int romanOrArabic(String[] strings, HashMap<String, Integer> numbers) throws IOException {
        if (numbers.containsKey(strings[0].toUpperCase()) &&
                numbers.containsKey(strings[2].toUpperCase())) {
            return 1;
        } else if (numbers.containsKey(strings[0].toUpperCase()) &&   //проверка случая когда одна цифра римская, другая не римская
                  !(numbers.containsKey(strings[2].toUpperCase())) ||
                  (!(numbers.containsKey(strings[0].toUpperCase())) &&
                  numbers.containsKey(strings[2].toUpperCase()))) {
            throw new IOException();
        } else if ((0 < Integer.parseInt(strings[0]) && Integer.parseInt(strings[0]) <= 10) &&
                (0 < Integer.parseInt(strings[2]) && Integer.parseInt(strings[2]) <= 10)) {
            return 2;
        } else {
            throw new IOException();
        }
    }

    /**
     * переводит римские цифры в арабские, запускает метод calculate(),
     * если результат положительный для вывода использует метод showRomanNumbers()
     * @param strings
     * @param numbers
     * @throws IOException
     */
    static void calculateRoman (String[] strings, HashMap<String, Integer> numbers) throws IOException {
        int a = numbers.get(strings[0].toUpperCase());
        int b = numbers.get(strings[2].toUpperCase());
        int result = calculate(a, b, strings[1]);
        if (result > 0) {
            System.out.println(showRomanNumber(result));
        } else {
            throw new IOException();
        }
    }

    /**
     * берет цифры с массива строк, запускает метод calculate()
     * @param strings
     */
    static void calculateArabic(String[] strings){
        int a = Integer.parseInt(strings[0]);
        int b = Integer.parseInt(strings[2]);
        System.out.println(calculate(a, b, strings[1]));
    }

    /**
     * производит математические операции
     * @param a
     * @param b
     * @param str
     * @return
     */
    static int calculate(int a, int b, String str) {
        if (str.equals("+")) {
            return a + b;
        } else if (str.equals("-")) {
            return a - b;
        } else if (str.equals("*")) {
            return a * b;
        } else {
            return a / b;
        }
    }

    /**
     * переводит результат с арабских цифр на римские
     * @param number
     * @return
     */
    static String showRomanNumber(int number) {
        if (number == 0) {   //условие необъодхимое для обработки чисел 20, 30
            return "";
        } else if (number == 1) {
            return "I";
        } else if (number == 2) {
            return "II";
        } else if (number == 3) {
            return "III";
        } else if (number == 4) {
            return "IV";
        } else if (number == 5) {
            return "V";
        } else if (number == 6) {
            return "VI";
        } else if (number == 7) {
            return "VII";
        } else if (number == 8) {
            return "VIII";
        } else if (number == 9) {
            return "IX";
        } else if (number == 10) {
            return "X";
        } else if (number > 10 && number < 20 ) {
            return "X" + showRomanNumber(number % 10);
        } else if (number >= 20 && number < 30 ) {
            return "XX" + showRomanNumber(number % 10);
        } else if (number >= 30 && number < 40 ) {
            return "XXX" + showRomanNumber(number % 10);
        } else if (number >= 40 && number < 50 ) {
            return "XL" + showRomanNumber(number - 40);
        } else if (number >= 50 && number < 60 ) {
            return "L" + showRomanNumber(number - 50);
        } else if (number >= 60 && number < 70 ) {
            return "L" + showRomanNumber(number - 50);
        } else if (number >= 70 && number < 80 ) {
            return "L" + showRomanNumber(number - 50);
        } else if (number >= 80 && number < 90 ) {
            return "L" + showRomanNumber(number - 50);
        } else if (number >= 90 && number < 100 ) {
            return "XC" + showRomanNumber(number - 90);
        } else {   //последний вариант, т.к. при данных условиях ТЗ результат больше 100 не получить
            return "C";
        }
    }
}