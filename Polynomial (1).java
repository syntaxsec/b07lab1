public class Polynomial {
    public double[] coeff = new double[100];

    public Polynomial() {
        coeff[0] = 0.0;
    }

    public Polynomial(double[] arr) {
        coeff = arr;
    }
    public Polynomial add(Polynomial b) {
        // Adds two polynomials
        double[] newq = new double[100];
        double[] larger = b.coeff.clone();
        double smaller[] = coeff;
        int size = b.coeff.length;
      
        if (size < coeff.length) {
          size = coeff.length; 
          larger = coeff.clone(); smaller = b.coeff.clone();
        }
        
        for (int i = 0; i < size-1; i++) {
            newq[i] = larger[i];
        }
        for (int i = 0; i < size-1; i++) {
            newq[i] += smaller[i];
        }
        Polynomial c = new Polynomial(newq);
        return c;
    }

    public double evaluate(double x) {
        // Evaluates polynomial
        double result = 0;
        double temp = 0;
        for (int i = 0; i < coeff.length-1; i++) {
          temp = Math.pow(x, i);
            result += coeff[i]*temp;
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}