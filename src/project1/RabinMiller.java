package project1;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RabinMiller {

    private static final BigInteger ONE = new BigInteger("1");
    private static final BigInteger TWO = new BigInteger("2");

    public void generate100Primes(Scanner s){
        System.out.println("Enter bitsize: ");
        int bitSize = s.nextInt();
        s.close();

        generatePrimes(100, bitSize);
    }

    public void generateTwoPrimes(Scanner s){
        System.out.println("Enter bitsize: ");
        int bitSize = s.nextInt();
        s.close();

        ArrayList<BigInteger> primes = generatePrimes(2, bitSize);
        System.out.println("Generated primes: \n" +
                "1: " + primes.get(0) +"\n" +
                "2: " + primes.get(1));
    }

    private ArrayList<BigInteger> generatePrimes(int primesAmount, int bitSize){
        int iterations = 20;

        Random rand = new Random();
        ArrayList<BigInteger> primes = new ArrayList<>();

        long timeStart = System.nanoTime();
        while(primes.size() < primesAmount){
            BigInteger testPrime = new BigInteger(bitSize, rand);
            if(testPrime.mod(TWO).equals(BigInteger.valueOf(0))){ //Check if number is even
                continue;
            }
            if(calculate(testPrime, iterations)){
                primes.add(testPrime);
            }
        }

        long timeEnd = System.nanoTime();
        System.out.println("Primes generated: " + primes.size() + " primes of bitsize " + bitSize);
        System.out.println("Time Elapsed: " + TimeUnit.NANOSECONDS.toSeconds((timeEnd-timeStart)) + "s");
        System.out.println("Average calculation time of 1 prime: " + TimeUnit.NANOSECONDS.toMillis((timeEnd-timeStart)/primesAmount) + "ms");

        return primes;
    }

    private boolean calculate(BigInteger n, int iterations){
        Random rand = new Random();
        BigInteger s = n.subtract(ONE);
        int r = 0;
        while (s.mod(TWO).equals(BigInteger.ZERO)) {
            s = s.divide(TWO);
            r++;
        }

        rabinMiller: for(int i = 0; i < iterations; i++){
            //Generates a random integer in the range [2, n-2]
            BigInteger a;
            do {
                a = new BigInteger(n.bitLength(), rand);
            } while (a.compareTo(TWO) < 0 && a.compareTo(n.subtract(TWO)) > 0);

            BigInteger x = a.modPow(s, n);
            if(x.equals(ONE) || x.equals(n.subtract(ONE))){
                continue;
            }
            for(int j = 1; j < r; j++){
                x = a.modPow(TWO.pow(j).multiply(s), n);
                if(x.equals(ONE)){
                    return false;
                }
                if(x.equals(n.subtract(ONE))){
                    continue rabinMiller;
                }
            }
            return false;
        }
        return true;
    }
}
