package util;

public class Calculator{
    private double lastResult;

    public Calculator(){
	lastResult = 0;
    }
    public double add(double n1, double n2){
	double result = n1+n2;
	lastResult = result;
	return result;
    }
    public double getLastResult(){
	return lastResult;
    }
}
