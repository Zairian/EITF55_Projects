package project1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final BigInteger TWO = new BigInteger("2");
    public static void main(String[] args) {
        RabinMiller rabinMiller = new RabinMiller();

        System.out.println("What generation do you want to execute: \n" +
                "1: Generation of 100 primes of selectable bitsize\n" +
                "2: Generation of 2 primes of bitsize 512 for RSA");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        switch (choice){
            case 1:
                rabinMiller.generate100Primes(in);
                break;
            case 2:
                rabinMiller.generateTwoPrimes(in);
                break;
        }
    }


}
