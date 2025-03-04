package com.ontariotechu.sofe3980U;

import java.math.BigInteger;

/**
 * Unsigned integer Binary variable
 */
public class Binary {
    private String number = "0";  // string containing the binary value

    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of binary digits (only '0' or '1'). If null, empty,
     *               or invalid, defaults to "0". Leading zeros are removed.
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0";
            return;
        }
        // Validate: only allow '0' and '1'
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0";
                return;
            }
        }
        // Remove leading zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }
        this.number = (beg == number.length()) ? "0" : number.substring(beg);
        if (this.number.isEmpty()) {
            this.number = "0";
        }
    }

    /**
     * Returns the binary value as a String.
     */
    public String getValue() {
        return this.number;
    }

    /**
     * Returns the sum of two binary numbers.
     */
    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        int carry = 0;
        String num3 = "";
        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;
            if (ind1 >= 0) {
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
                ind1--;
            }
            if (ind2 >= 0) {
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
                ind2--;
            }
            carry = sum / 2;
            sum = sum % 2;
            num3 = ((sum == 0) ? "0" : "1") + num3;
        }
        return new Binary(num3);
    }

    /**
     * Returns the product of two binary numbers.
     */
    public static Binary multiply(Binary num1, Binary num2) {
        BigInteger a = new BigInteger(num1.number, 2);
        BigInteger b = new BigInteger(num2.number, 2);
        BigInteger product = a.multiply(b);
        return new Binary(product.toString(2));
    }

    /**
     * Returns the bitwise AND of two binary numbers.
     */
    public static Binary and(Binary num1, Binary num2) {
        BigInteger a = new BigInteger(num1.number, 2);
        BigInteger b = new BigInteger(num2.number, 2);
        BigInteger result = a.and(b);
        return new Binary(result.toString(2));
    }

    /**
     * Returns the bitwise inclusive OR of two binary numbers.
     * (Operator "I" is used for this operation.)
     */
    public static Binary or(Binary num1, Binary num2) {
        BigInteger a = new BigInteger(num1.number, 2);
        BigInteger b = new BigInteger(num2.number, 2);
        BigInteger result = a.or(b);
        return new Binary(result.toString(2));
    }
}
