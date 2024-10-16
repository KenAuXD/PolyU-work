package hk.edu.polyu.comp.comp2021.assignment1.complex;

public class Complex {

    // Task 5 : add the missing fields
    Rational real;
    Rational imag;


    // Task 6: Complete the constructor as well as the methods add, subtract, multiply, divide, and toString.
    public Complex(Rational real, Rational imag) {
        // Todo: complete the constructor
        setReal(real);
        setImag(imag);
    }


    public Complex add(Complex other) {
        // Todo: complete the method
        Rational i = this.real.add(other.real);
        Rational j = this.imag.add(other.imag);
        return new Complex(i, j);
    }

    public Complex subtract(Complex other) {
        // Todo: complete the method
        Rational i = this.real.subtract(other.real);
        Rational j = this.imag.subtract(other.imag);
        return new Complex(i, j);
    }

    public Complex multiply(Complex other) {
        // Todo: complete the method
        // (a+bi)*(c+di) = (ac + adi + cbi + -bd) = (ac-bd) + (ad+cb)i 
        Rational i = this.real.multiply(other.real).subtract(this.imag.multiply(other.imag));
        Rational j = this.real.multiply(other.imag).add(other.real.multiply(this.imag));
        return new Complex(i, j);
    }

    public Complex divide(Complex other) {
        // Todo: complete the method
        // you may assume 'other' is never equal to '0+/-0i'.
        //(a+bi)/(c+di) = (a+bi)(c-di)/(c^2+d^2) = (ac+bd)/(c^2+d^2) + (cb-ad)i/(c^2+d^2) 
        Rational d = other.real.multiply(other.real).add(other.imag.multiply(other.imag)); //(c^2+d^2)
        Rational i = this.real.multiply(other.real).add(this.imag.multiply(other.imag)); //(ac+bd)
        Rational j = other.real.multiply(this.imag).subtract(this.real.multiply(other.imag)); //(cb-ad)
        return new Complex(i.divide(d), j.divide(d));
    }

    public void simplify() {
        // Todo: complete the method
        real.simplify();
        imag.simplify();
    }

    public String toString() {
        // Todo: complete the method
        return "(" + real + "," + imag + ")";
    }

    // =========================== Do not change the methods below


    private Rational getReal() {
        return real;
    }

    private void setReal(Rational real) {
        this.real = real;
    }

    private Rational getImag() {
        return imag;
    }

    private void setImag(Rational imag) {
        this.imag = imag;
    }
}
