package tristan;

import Jama.Matrix;

public class TestJama {
public static void main(String[] args) {
	double[][] array = {{1.,2.,3},{0.,5.,6.},{0.,0.,9.}};
	double[][] array2 = {{1},{2},{3}};
	Matrix a = new Matrix(array);
	Matrix sss = a.inverse();
	
	Matrix b = new Matrix(array2);
	Matrix x = a.times(b);

	
	System.out.println(x.getArray());
	
}
}
