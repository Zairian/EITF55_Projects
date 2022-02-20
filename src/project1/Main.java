package project1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        BigInteger e = BigInteger.TWO;
        e = e.pow(16).add(BigInteger.ONE);

        RabinMiller rabinMiller = new RabinMiller();

        System.out.println("What generation do you want to execute: \n" +
                "1: Generation of 100 primes of selectable bitsize\n" +
                "2: Generation of 2 primes of selectable bitsize for RSA");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        switch (choice){
            case 1:
                rabinMiller.generate100Primes(in);
                break;
            case 2:
                ArrayList<BigInteger> primes = rabinMiller.generateTwoPrimes(in);
                System.out.println("Do you want to compute and print RSA?\n" +
                        "1. Yes\n" +
                        "2. No");
                choice = in.nextInt();
                in.close();
                if(choice == 1){
                    BigInteger d = RSA.inverseModulo(e,
                            primes.get(0).subtract(BigInteger.ONE).multiply(primes.get(1).subtract(BigInteger.ONE)));
                    System.out.println("d = " + d);
                    RSA.calculateRSA(primes.get(0), primes.get(1), d, e);
                }
                break;
        }
    }
}
