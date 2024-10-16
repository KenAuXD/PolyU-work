package hk.edu.polyu.comp.comp2021.assignment1;

public class TinyFloat {

    public static final int TINY_FLOAT_SIZE = 8;
    public static final int SIGN_POS = 0;
    public static final int EXPONENT_POS = 1;
    public static final int MANTISSA_POS = 5;

    public static void main(String[] args){
        //System.out.println(fromString("10000000"));
        System.out.println(numberOfIntegers());
    }

    // Task 1a: Complete the method binary2Integer
    // to convert the string value to integer value for the exponent.
    private static int binary2Integer(String exponentString){
        int value = 0;
        for(int i = exponentString.length() - 1; i >= 0; i--){
            if(exponentString.charAt(exponentString.length() - i - 1) == '1') value += 1 << i;
        }
        return value;
    }

    // Task 1b: Complete the method binary2Decimal
    // to convert the string value to float value for the mantissa.
    private static float binary2Decimal(String mantissaString){
        float value = 1;
        int k = -1;
        for(int i = 0; i < mantissaString.length(); i++){
            if(mantissaString.charAt(i) == '1') value += Math.pow(2,k);
            //System.out.println(Math.pow(2,k));
            k--;
        }
        return value;

    }


    public static float fromString(String bitSequence){
        float result = 0;
        // Task 1c: Complete the method fromString based on the two methods,
        // binary2Integer and binary2Decimal.
        String exponent = bitSequence.substring(EXPONENT_POS, MANTISSA_POS);
        String mantissa = bitSequence.substring(MANTISSA_POS, TINY_FLOAT_SIZE);   
        int sign = (bitSequence.charAt(0) == '1') ? -1 : 1;
       // System.out.println(exponent + " " + mantissa);
       // System.out.println(sign);
        result =  (float) (sign * binary2Decimal(mantissa) * Math.pow(2, binary2Integer(exponent)));
        return result;
    }



    public static int numberOfIntegers(){
        // Task 2: return the number of TinyFloat object values that are integers
        String[] s = getValidTinyFloatBitSequences();
        int k = 1;
        for (String sequence : s) {
              if(fromString(sequence) == k){
                //System.out.println(fromString(sequence));
                System.out.println(sequence + " == " + k);
                k++;
              }
        }
        return k - 1;
    }

    /**
     * Get all valid bit sequences for tinyFloat values.
     * Do not change the function.
     */
    private static String[] getValidTinyFloatBitSequences(){
        int nbrValues = (int)Math.pow(2, TINY_FLOAT_SIZE);

        String[] result = new String[nbrValues];
        for(int i = 0; i < nbrValues; i++){
            result[i] = String.format("%" + TINY_FLOAT_SIZE + "s", Integer.toBinaryString(i))
                    .replace(' ', '0');
        }
        return result;
    }


}
