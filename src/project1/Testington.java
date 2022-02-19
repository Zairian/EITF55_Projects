package project1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Testington {
    private static final BigInteger TWO = new BigInteger("2");
    public static void main(String[] args) {
        //BigInteger integerToBeTested = new BigInteger("531137992816767098689588206552468627329593117727031923199444138200403559860852242739162502265229285668889329486246501015346579337652707239409519978766587351943831270835393219031728127");
        int iterations = 20;
        int amountPrimesToGenerate = 100;

        RabinMiller rabinMiller = new RabinMiller();
        ArrayList<BigInteger> primes = new ArrayList<>();

        long timeStart = System.nanoTime();
        while(primes.size() < amountPrimesToGenerate){
            Random rand = new Random();
            BigInteger testPrime = new BigInteger(512, rand);
            if(testPrime.mod(TWO).equals(BigInteger.valueOf(0))){ //Check if number is odd
                continue;
            }
            if(rabinMiller.calculate(testPrime, iterations)){
                primes.add(testPrime);
            }
        }
        long timeEnd = System.nanoTime();
        System.out.println("Time Elapsed: " + (timeEnd-timeStart)/amountPrimesToGenerate);

        for(int i = 0; i < primes.size(); i++){
            //System.out.println(primes.get(i));
        }
        System.out.println("Primes generated: " + primes.size());
    }
}
