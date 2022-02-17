package project1;
import java.math.BigInteger;
import java.util.Random;

public class RabinMiller {

    private static final BigInteger ZERO = new BigInteger("0");
    private static final BigInteger ONE = new BigInteger("1");
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger FOUR = new BigInteger("4");


    public boolean calculate(BigInteger n, int iterations){
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
    /*
    public long computeModulo(long base, long exp, long mod){

        if(base == 0){
            return 0;
        }
        long res = 1;
        long y = base;
        long x = exp;
        while(x > 0){
            if(x % 2 != 0){
                res = (res*y) % mod;
            }
            y = (y*y) % mod;
            x = x/2;
        }
        return res;
    }*/

    /*public long computePow(long base, long exp){
        if(base == 0){
            return 0;
        }
        long y = base;
        long x = exp;
        long result = 1;

        while(x > 0){
            result *= y;
            x--;
        }
        return result;
    }*/

}
