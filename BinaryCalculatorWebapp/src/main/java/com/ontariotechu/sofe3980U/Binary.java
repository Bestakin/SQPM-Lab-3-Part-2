package com.ontariotechu.sofe3980U;

public class Binary {
    private String number = "0";

    public Binary(String number) {
        if (number == null || number.isEmpty() || !number.matches("[01]+")) {
            this.number = "0";
        } else {
            this.number = number.replaceFirst("^0+(?!$)", "");
        }
    }

    public String getValue() {
        return this.number;
    }

    // Addition method
    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        int carry = 0;
        StringBuilder num3 = new StringBuilder();

        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;
            if (ind1 >= 0) sum += num1.number.charAt(ind1--) - '0';
            if (ind2 >= 0) sum += num2.number.charAt(ind2--) - '0';

            carry = sum / 2;
            num3.insert(0, sum % 2);
        }
        return new Binary(num3.toString());
    }

    // Multiplication
    public static Binary multiply(Binary num1, Binary num2) {
        int n1 = Integer.parseInt(num1.number, 2);
        int n2 = Integer.parseInt(num2.number, 2);
        int product = n1 * n2;
        return new Binary(Integer.toBinaryString(product));
    }

    // Bitwise AND
    public static Binary and(Binary num1, Binary num2) {
        int n1 = Integer.parseInt(num1.number, 2);
        int n2 = Integer.parseInt(num2.number, 2);
        int result = n1 & n2;
        return new Binary(fixBinaryResult(Integer.toBinaryString(result)));
    }

    // Bitwise OR
    public static Binary or(Binary num1, Binary num2) {
        int n1 = Integer.parseInt(num1.number, 2);
        int n2 = Integer.parseInt(num2.number, 2);
        int result = n1 | n2;
        return new Binary(fixBinaryResult(Integer.toBinaryString(result)));
    }

    // Ensure empty binary outputs return "0"
    private static String fixBinaryResult(String result) {
        return result.isEmpty() ? "0" : result;
    }
}