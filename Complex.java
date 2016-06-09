import java.io.*;
import java.util.Scanner;
public class Complex {

/* @author Mohammad Naveed
 * 1332196, navedmu
 * Assignment#1 2XB3
 */
	//Instance variables
	
	private final double a;
	private final double b;
	
	
	//Constructor taking two arguments, one for the real part and the second for the imaginary part
	public Complex(double a, double b) {		
	this.a= a;
	this.b = b;
}
	public Complex (){			//Default constructor
		this.a = 0.0;
		this.b = 0.0;
	}
	
	public double getReal(){ return a;}			//Accessor method to get real part of the complex number
	
	public double getImaginary(){ return b;}		//Accessor method to get imaginary part of the complex number

	
	public Complex addition(Complex x){			//Add two complex numbers and returns the sum as type complex
		Complex j = new Complex(this.a + x.a, this.b+x.b);		//(a+c), (b+d)i
		return j;
	}
	
	public Complex subtraction(Complex x){		//Subtract two complex numbers and returns the difference as type complex
		   Complex j = new Complex(this.a - x.a, this.b-x.b);	//(a-c), (b-c)i
		   return j;
	}
	
	public Complex multiply(Complex x){			//Multiply two complex numbers and returns it as type Complex
		Complex j  = new Complex(this.a*x.a - this.b*x.b, this.b*x.a + this.a*x.b);		//(a*c-b*d),(b*c+a*d)i
		return j;
	}
	
	public Complex divide(Complex x){		//Divides two complex numbers and returns it as type Complex
		Complex j = new Complex((this.a*x.a + this.b*x.b)/(x.a*x.a + x.b*x.b),((this.b*x.a - this.a*x.b)/(x.a*x.a + x.b*x.b)));	 //(a*c + b*d)/(c*c + d*d),((b*c - a*d)/(c*c + d*d))i
		return j;			
	}
	
	public String sqrt() { 			//Gets the square root of your complex number in cartesian form
		double real1;
		double imaginary1;
		
		real1 = Math.sqrt((a+Math.sqrt(this.a*this.a + this.b*this.b))/2);			 //Calculating the real part of the first root
		imaginary1 = -1*Math.sqrt((Math.sqrt(this.a*this.a + this.b*this.b)-a)/2);   //Calculating the imaginary part of the first root
		
		Complex j = new Complex(real1,imaginary1);			
		return "±("+j+")";
	
	}
	
	public void polar(){					//Converts your cartesian complex number into polar form and prints it out with theta in degrees
		String s;
		double theta = 0.0;
		double r = Math.sqrt((this.a*this.a) + (this.b*this.b));			//Modulus
		double angle = Math.toDegrees(Math.atan(Math.abs(this.b/this.a)));	// angle = arctan(b/a)
		
		if(this.a<0){							//These if and else statements take care of the 4 quadrants and angles.
			if (this.b <0){
				theta = 180 + angle;}
			else { theta = 180 - angle;}
			}else
			{if (this.b<0){
				theta = 360-angle;
			}else
			{theta = angle;}
		}
		s = r + "(cos("+ theta +")"+ " +sin("+ theta + "i)" + ")";		// to get the form 'r(cos(theta) + isin(theta)'
		System.out.println(s);
		
		
	}
		
	public Complex conjugate(){			//Gets the conjugate of your complex number
		Complex j = new Complex(this.a, -1*this.b);
		return j;
	}
	
	public boolean equals(Complex x){		//Tests whether two complex numbers are equal
		if(this.a == x.a && this.b == x.b){
			return true;
		}
		else return false;
		}
	
	public String toString(){				//This method returns a complex number as a String representation
		if(this.b > 0){						//if statement takes care of the + or - sign in the String representation of the complex number
		return this.a + "+" + this.b + "i";}
		else return this.a + "-" + Math.abs(this.b) + "i";	}
	
	public Complex gcd(Complex x, Complex y){			//calculates the greatest common divisor of two complex numbers
		if (y.b == 0){return x;}
		Complex u = new Complex();
		u = x.divide(y);
		Complex potential_divisor = new Complex(Math.floor(u.a),Math.floor(u.b));
		Complex k = x.subtraction(y.multiply(potential_divisor));
		return gcd(y,k);
	}
	
	public  Complex rotate(Complex a, int angle){				//This method rotates the complex number by the given angle.
		double theta = 0.0;											//First the complex number is converted to polar form, rotated then converted back to cartesian form.
		double real;
		double imaginary;
		double rotated_angle = 0.0;
		double r = Math.sqrt((a.a*a.a) + (a.b*a.b));			//Modulus
		double angle2 = Math.toDegrees(Math.atan(Math.abs(a.b/a.a)));	//angle2 = arctan(b/a)
		
		if(a.a<0){											//These if and else statements take care of the 4 quadrants and angles.
			if (a.b <0){
				theta = 180 + angle2;}
			else { theta = 180 - angle2;}
			}else
			{if (a.b<0){
				theta = 360-angle2;
			}else
			{theta = angle2;}
		}
		
		rotated_angle = theta - (double)angle;
		real = r*Math.cos(Math.toRadians(rotated_angle));		//converting real part of the complex number from polar to cartesian
		imaginary = r*Math.sin(Math.toRadians(rotated_angle));	//converting imaginary part of the complex number from polar to cartesian
		
		if(Math.abs(real) < 0.00000000001){					//This if statement allows the value of cos(90) and cos(270) to be represented accurately, 
			real = 0.0;										//because (pi/2) cannot be accurately reperesented in IEEE 754 notation, cos(90) or cos(270)
															//will return a very small value close to 0, but not exactly 0, without the if statement.	
		}
		if(Math.abs(imaginary) < 0.00000000001){
			imaginary = 0.0;
		}
		
		Complex j = new Complex(real, imaginary);			
		return j;
	}
	
	public Complex get_current(Complex v, Complex z){		//Gets current through I=V/R
		Complex i = v.divide(z);
		return i;
	}
	
	public Complex get_voltage(Complex i, Complex z){		//Gets voltage through V = I*R
		Complex v = i.multiply(z);
		return v;
	}
	
	public static void main(String[] args) {
			
	
	}
}
	
