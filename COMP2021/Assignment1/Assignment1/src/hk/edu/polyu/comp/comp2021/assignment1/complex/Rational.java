package hk.edu.polyu.comp.comp2021.assignment1.complex;

public class Rational {

    // Task 3: add the missing fields
    int numerator;
    int denominator;
    
    // Task 4: 	Complete the constructor and
    // the methods add, subtract, multiply, divide, simplify, and toString.

    public Rational(int numerator, int denominator){
        // Todo: complete the constructor
        setNumerator(numerator);
        setDenominator(denominator);
    }

    public Rational add(Rational other){
        // Todo: complete the method
        // a/b+c/d = (ad+bc)/bd
        int i = this.numerator * other.denominator + this.denominator * other.numerator;
        int j = this.denominator * other.denominator;
        return new Rational(i, j);
    }

    public Rational subtract(Rational other){
        // Todo: complete the method
        int i = this.numerator * other.denominator - this.denominator * other.numerator;
        int j = this.denominator * other.denominator;
        return new Rational(i, j);
    }

    public Rational multiply(Rational other){
        // Todo: complete the method
        // a/b*c/d = ac/bd
        int i = this.numerator * other.numerator;
        int j = this.denominator * other.denominator;
        return new Rational(i, j);
    }

    public Rational divide(Rational other){
        // Todo: complete the method
        // a/b / c/d = ad/bc
        int i = this.numerator * other.denominator;
        int j = this.denominator * other.numerator;
        return new Rational(i, j);
    }

    public String toString(){
        // Todo: complete the method
        return numerator + "/" + denominator;

    }

    public void simplify(){
        // Todo: complete the method
        if(denominator < 0){
            numerator *= -1;
            denominator *= -1;
        }
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
    }

    // ========================================== Do not change the methods below.

    private int getNumerator() {
        return numerator;
    }

    private void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    private int getDenominator() {
        return denominator;
    }

    private void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    private int gcd(int a, int b){
        if(b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

}
