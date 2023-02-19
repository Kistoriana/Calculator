package org.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class Main {
    public static boolean isIntParsable(String text){
        try {
            Integer.parseInt(text);
            return true;
        } catch(NumberFormatException ex){
            return false;
        }
    }

    public static String Units(int unit) {
        String Unit = "";
        switch (unit) {
            case 1: Unit = "I";
                break;
            case 2: Unit = "II";
                break;
            case 3: Unit = "III";
                break;
            case 4: Unit = "IV";
                break;
            case 5: Unit = "V";
                break;
            case 6: Unit = "VI";
                break;
            case 7: Unit = "VII";
                break;
            case 8: Unit = "VIII";
                break;
            case 9: Unit = "IX";
                break;
        }
        return Unit;
    }

    public static String Tens(int tens) {
        String Ten = "";
        switch (tens) {
            case 1: Ten = "X";
                break;
            case 2: Ten = "XX";
                break;
            case 3: Ten = "XXX";
                break;
            case 4: Ten = "XL";
                break;
            case 5: Ten ="L";
                break;
            case 6: Ten = "LX";
                break;
            case 7: Ten = "LXX";
                break;
            case 8: Ten = "LXXX";
                break;
            case 9: Ten = "XC";
                break;
            case 10: Ten = "C";
                break;
        }
        return Ten;
    }

    public static String calc(String input) throws IOException {
        String[] str = input.split(" ");
        String[] Rom = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int num1;
        int num2;
        boolean isRom = false;

        if (str.length != 3) {
            throw new IOException("Неверный формат строки");
        }

        if (isIntParsable(str[0]) && isIntParsable(str[2])) {
            num1 = parseInt(str[0]);
            num2 = parseInt(str[2]);
            if ((num1 < 1 || num1 > 10) || (num2 <1 || num2 > 10)){
                throw new IOException("Ошибка. Введите числа от 1 до 10 (от I до X)");
            }
        } else if(!isIntParsable(str[0]) && !isIntParsable(str[2])) {
            if (Arrays.asList(Rom).contains(str[0]) && Arrays.asList(Rom).contains(str[2])){
                num1 = Arrays.asList(Rom).indexOf(str[0]) + 1;
                num2 = Arrays.asList(Rom).indexOf(str[2]) + 1;
                isRom = true;
            } else throw new IOException("Ошибка. Введите числа от 1 до 10 (от I до X)");
        } else throw new IOException("Неверный формат строки. Введите числа от 1 до 10 (от I до X)");

        char op = str[1].charAt(0);
        int res;

        switch (op){
            case '+' :
                res = num1 + num2;
                break;
            case '-' :
                res = num1 - num2;
                break;
            case '*' :
                res = num1 * num2;
                break;
            case '/' :
                res = num1 / num2;
                break;
            default:
                throw new RuntimeException("Неподдерживаемая операция");
        }
        if (isRom){
            if (res <= 0) {
                throw new RuntimeException("В римской системе только положительные числа");
            }

            int tens1 = res/10;
            int tens = res%100;
            int units = res%10;

            return Tens(tens1)+Tens(tens)+Units(units);
        }
        return Integer.toString(res);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую операцию:");
        String inputString = scanner.nextLine();
        System.out.println(calc(inputString));
    }
}
