package project1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final BigInteger TWO = new BigInteger("2");
    public static void main(String[] args) {
        int bitSize = 512;
        int iterations = 20;
        int amountPrimesToGenerate = 100;

        Random rand = new Random();

        RabinMiller rabinMiller = new RabinMiller();
        ArrayList<BigInteger> primes = new ArrayList<>();

        System.out.println("What generation do you want to execute: \n" +
                "1: Generation of 100 primes of selectable bitsize\n" +
                "2: Generation of 2 primes of bitsize 512 for RSA");

        long timeStart = System.nanoTime();
        while(primes.size() < amountPrimesToGenerate){
            BigInteger testPrime = new BigInteger(bitSize, rand);
            if(testPrime.mod(TWO).equals(BigInteger.valueOf(0))){ //Check if number is even
                continue;
            }
            if(rabinMiller.calculate(testPrime, iterations)){
                primes.add(testPrime);
            }
        }

        long timeEnd = System.nanoTime();
        System.out.println("Primes generated: " + primes.size() + " primes of bitsize " + bitSize);
        System.out.println("Time Elapsed: " + TimeUnit.NANOSECONDS.toSeconds((timeEnd-timeStart)) + "s");
        System.out.println("Average calculation time of 1 prime: " + TimeUnit.NANOSECONDS.toMillis((timeEnd-timeStart)/amountPrimesToGenerate) + "ms");
    }
}
