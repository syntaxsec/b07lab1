import java.io.File;
importjava.util.FileReader;
import Java.util.Scanner;


public class Polynomial {
  public double[] coeff = new double[100];
  public int[] exponents = new int[100];

  public Polynomial() {
    //Default size 100:
    coeff[0] = 0.0;
    exponents[0] = 0;
  }

  public Polynomial(double[] arr, int[] arr2) {
    coeff = arr.clone();
    exponents = arr2.clone();
  }

  public Polynomial(File c) throws Exception{
   Scanner e = new Scanner(c);
   int j = 0;
    while (e.hasNextLine) {
      String data = e.nextLine();
      Char[] s = new Char[data.size]; // Check String.size
      if (data.charAt(j) != '-') {s[j] = '+';}
      j++;
      for (int i = 0; i < data.length; i++) {
        if (data.charAt(i) == '-') {s[j] = '-';}
        if (data.charAt(i) == '+') {s[j] = '+';}
      }
      int f = 0;
      String[] temp = new String[2];
      String[] rg = data.split("[-+]");
      for (int i = 0; i < rg.length; i++) {
        if (!rg[i].contains('x')) {exponents[f] = 0; coeff[f] = Double.parseDouble(rg[i]); f++;}
        temp = rg[i].split("x");
        coeff[f] = Double.parseDouble(temp[0]);
        exponents[f] = Int.parseInt(temp[1]);
        f++;
      }

      for(int i = 0; i < coeff.length; i++) {if (s[i] == '-') {coeff[i] *= -1.0;}} // Add back negative signs.
    }
  }



  public void remove(double[] c, int[] e) {
    int size = 0;
    for (int i = 0; i < c.size; i++) {if (c[i] != 0){size++;}}
    if (size == 0) {return;}
    double[] newc = new double[size];
    int[] newe = new int[size];
    int j = 0;
    for (int i = 0; i < newc.size; i++) {
      if (c[i] != 0) {
        newc[j] = c[i];
        newe[j] = e[i];
        j++;
      }
      c = newc.clone();
      e = newe.clone();
    }
  }

  public Polynomial add(Polynomial b) {
    // Adds two polynomials
    Polynomial c = new Polynomial()
    double[] newcoff = new double[100];
    int[] newexp = new int[100];
    int j = 0;
    int temp = 0;

    for (int i = 0; i < this.coeff.length; i++) { //Covers non-common terms
      if (!b.exponents.contains(this.exponents[i])) {
        newexp[j] = this.exponents[i];
        newcoff[j] = this.coeff[i];
        j++;
      }
    }

    for(int i = 0; i < b.coeff.length; i++) {
      if (!this.exponents.contains(b.exponents[i])) {
        newexp[j] = b.exponents[i];
        newcoff[j] = b.coeff[i];
      }


      for (int r = 0; r < this.coeff.length; r++) {
        if (b.exponents[i] == this.exponents[r]) {temp = r; break;} 
      }


      if (b.coeff[i] + this.coeff[temp] != 0) {
        newexp[j] = b.coeff[j];
        newcoff[j] = b.coeff[i] + this.coeff[temp];
      }
    }
    remove(newcoff, newexp);
    Polynomial c = new Polynomial(newcoff, newexp);
    return c;
  }


  public double evaluate(double x) {
    // Evaluates polynomial
    double result = 0;
    double temp = 0;
    for (int i = 0; i < coeff.length; i++) {
      temp = Math.pow(x, this.exponents[i]);
      result += coeff[i] * temp;
    }
    return result;
  }

  public boolean hasRoot(double x) {
    return evaluate(x) == 0;
  }

public void removeIndex(double[] c, int[] e, int ind) {
    double[] newc = new double[c.size - 1];
    int[] newe = new int[e.size - 1];
    int j = 0;
    for (int i = 0; i < newc.size; i++) {
      if (i != ind) {
        newc[j] = c[i];
        newe[j] = e[i];
        j++;
      }
      c = newc.clone();
      e = newe.clone();
    }
}

  public void redundancy(int[] exps, double[] ceffs) {
    for (int i = 0; i < exps.length; i++) {
      for (int j = 1; j < exps.length; j++) {
        if (exps[i] == exps[j]) {
          ceffs[i] += ceffs[j];
          removeIndex(ceffs, exps, j);
        }
      }
    }
    remove(ceffs, exps);
  }

  public Polynomial multiply(Polynomial b) {
    double ceffs[] = new double[100];
    int exps[] = new int[100];
    // Messy multiply and add:
    for (int i = 0; i < this.exponents.length; i++) {
      for (int j = 0; j < b.exponents.length; j++) {
        ceffs[i] = this.coeffs[i]*b.coeffs[j];
        exps[i] = this.exponents[i]*b.exponents[j];
      }
    }

    //Remove redundancies:
    for (int i = 0; i < newe.length; i++) {
      if (newc[i] == 0) {remove(newe, newc, i);} //0 coeff removal
      redundancy(exps, ceffs);
    }
    Polynomial c = new Polynomial(ceffs, exps);
    return c;
  }
}
