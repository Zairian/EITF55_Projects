package project1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class RSA {
    BigInteger p = new BigInteger("4743426623114357574183071938267546309464563605626416261501831597167642198859672441033378308027137733051094009779539684940033634962555473353932462454968489");
    BigInteger q = new BigInteger("7037846743749524358958253985673845435571657551550331036523217561839793602667557104310731409807518391145751067918726120227715930575252016970388591199024673");

    public static BigInteger inverseModulo(BigInteger a, BigInteger m){
        BigInteger d1 = m;
        BigInteger d2 = a;
        BigInteger v1 = BigInteger.ZERO;
        BigInteger v2 = BigInteger.ONE;

        while(!d2.equals(BigInteger.ZERO)){
            BigInteger q = d1.divide(d2);
            BigInteger t2 = v1.subtract(q.multiply(v2));
            BigInteger t3 = d1.subtract(q.multiply(d2));
            v1 = v2;
            v2 = t2;
            d1 = d2;
            d2 = t3;
        }
        BigInteger v = v1;
        BigInteger d = d1;
        if(!d.equals(BigInteger.ONE)){
            return null;
        }
        return v;
    }

    public static void calculateRSA(BigInteger p, BigInteger q, BigInteger d, BigInteger e){
        Random rand = new Random();
        BigInteger N = p.multiply(q);
        BigInteger s;
        do {
            s = new BigInteger(N.bitLength(), rand);
        } while (s.compareTo(BigInteger.ONE) <= 0 || s.compareTo(N) >= 0);

        System.out.println("s = " + s);

        BigInteger c = s.modPow(e, N);

        System.out.println("c = " + c);

        BigInteger z = c.modPow(d, N);

        System.out.println("z = " + z);

        if(s.equals(z)){
            System.out.println("RSA encryption successful");
        } else{
            System.out.println("RSA encryption failed");
        }
    }
}
