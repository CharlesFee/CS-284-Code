//Charles Fee I pledge my honor that I have abided by the Stevens Honor System
import java.lang.Number;
import java.lang.Math;
public class RationalNumber{
    int numerator;
    int denominator;
    public RationalNumber(int p, int q){
	if(q == 0){
	    throw new ArithmeticException("/ by zero");
	}else{
	    numerator = p;
	    denominator = q;
	}
    }
    public RationalNumber(String s){
	int x = 0;
	int y = 0;
	String characteristic = "";
	String antiperiod = "";
	String period = "";
	//breaks up the binary into the characteristic antiperiod and period sections
	for(int i = 0; i < s.length(); i++){
	    if(s.charAt(i)=='.'){
	        x = i;
		characteristic = s.substring(0,x);
	    }
	    if(s.charAt(i)=='_'){
		y = i;
		antiperiod = s.substring(x+1,y);
		period = s.substring(y+1,s.length());
		break;
	    }
	    
	}
	int characteristicValueNum = 0;
	int characteristicValueDen = 1;
	int antiperiodValueNum = 0;
	int antiperiodValueDen = 0;
	int periodValueNum = 0;
	int periodValueDen = 0;
	//Calculates the characteristic value from binary to base 10
	for(int i = 0; i < characteristic.length();i++){
	    if(characteristic.charAt(i)=='1'){
		characteristicValueNum+= Math.pow(2,characteristic.length()-i-1);
	    }
	}
	//calcuates the antiperiods numerator
	for(int i = 0; i < antiperiod.length();i++){
	    if(antiperiod.charAt(i)=='1'){
		antiperiodValueNum+= Math.pow(2,antiperiod.length()-i-1);
	    }
	}
	//antiperiods denom
	antiperiodValueDen =(int) Math.pow(2,antiperiod.length());
	//Lastly calculates the periods denom and num
	for(int i = 0; i < period.length();i++){
	    if(period.charAt(i)=='1'){
		periodValueNum+= Math.pow(2,period.length()-i-1);
	    }
	}
	periodValueDen = (int)(Math.pow(2,(period.length()))-1)*(int)Math.pow(2,(antiperiod.length()));
	//Slaps all of the numerators and denominators together and then simplifies
	int num = ((((characteristicValueNum*antiperiodValueDen)+(characteristicValueDen*antiperiodValueNum))*periodValueDen)+(periodValueNum*characteristicValueDen*antiperiodValueDen));
	int den = characteristicValueDen*antiperiodValueDen*periodValueDen;
	int gcdd = gcd(num,den);
	numerator = num/gcdd;
	denominator = den/gcdd;
	
    }
    //finds the greatest common denominator between a adn b
    static int gcd(int a, int b){
	int gcd = 1;
	for(int i = 1; i <= a && i <= b; ++i)
        {
            // Checks if i is a factor of both integers
            if(a % i==0 && b % i==0)
                gcd = i;
        }
	return gcd;
    }
    //simplifies the num and denom
    void simplify(){
	int gcd =gcd(numerator,denominator);
	this.numerator = numerator/gcd;
	this.denominator = denominator/gcd;
    }
    //adds two rational numbers
    void add(RationalNumber r){
	this.numerator = (this.numerator*r.denominator)+(this.denominator*r.numerator);
	this.denominator = this.denominator*r.denominator;
	simplify();
    }
    //multiplies two rational numbers
    void multiply(RationalNumber r){
	this.numerator = this.numerator*r.numerator;
	this.denominator = this.denominator*r.denominator;
	simplify();
    }
    //checks to see if the rational numbers are equal
    public boolean isEqual(RationalNumber other){
	return this.numerator == other.numerator && this.denominator == other.denominator;
    }
    //truncates to only 3 decimal points
    double truncate(double d){
	return Math.floor(d*1e3)/1e3;
    }
    //gets you the value of the rational number as if it was a double to 3 decimals
    double doubleValue(){
	return truncate((double)(this.numerator)/this.denominator);
    }
     //gets you the value of the rational number as if it was a float to 3 decimals
    float floatValue(){
	return (float)truncate((double)(this.numerator)/this.denominator);
    }
     //gets you the value of the rational number as if it was an int
    int intValue(){
	return this.numerator/this.denominator;
    }
     //gets you the value of the rational number as if it was a long
    long longValue(){
	return this.numerator/this.denominator;
    }
}
