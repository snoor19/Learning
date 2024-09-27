package com.spero.learn;
public class PrintAlphabetWithNumbers {
    public static void main(String[] args) {
        // Print letters A to Z along with their corresponding numeric values
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            System.out.println(letter + "-" + (letter - 'A' + 1));
        }
    }
}