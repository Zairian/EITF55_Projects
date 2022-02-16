package project1;
import java.math.BigInteger;
import java.util.Random;

public class RabinMiller {

    public boolean calculate(BigInteger n, BigInteger iterations){
        Random rand = new Random();
        BigInteger a = new BigInteger(String.valueOf(rand.nextInt(n.intValue()-2) + 2));
        //long x = computeModulo(a, n-1, n);
        BigInteger x = a.modPow(n.subtract(new BigInteger("2")), n);

        if (x.intValue()==1 || x.intValue() == n.intValue()-1) {
            return true;
        }
        for(int j = 1; j <= iterations.intValue()-1; j++){
            //x = computeModulo(a, computePow(2, j)*(n-1), n);
            //x = computePow(a, computePow(2,j)*(n-1)) % n;
            x = a.modPow(new BigInteger("2").pow(j), n.subtract(new BigInteger("1")));
            if(x.intValue() == 1){
                return false;
            }
            if(x.intValue() == n.intValue()-1){
                return true;
            }
        }
        return false;
        BigInteger.probablePrime()
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
