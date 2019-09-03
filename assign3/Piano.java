/*
 * Piano.java
 */

package assign3;


public class Piano extends Instrument{

    private static InstString[][] pStrings; //2D array of strings
    
    public Piano(int numNotes){
	pStrings = new InstString[numNotes][3];
	for(int i = 0; i < pStrings.length; i++){
	    pStrings[i][0] = new GuitarString((440*Math.pow(2,(i-24)/12.0))-.45);
	    pStrings[i][1] = new GuitarString(440*Math.pow(2,(i-24)/12.0));
	    pStrings[i][2] = new GuitarString((440*Math.pow(2,(i-24)/12.0))+.45);
	}
    }
  
    public void playNote(int index){
	pStrings[index][0].pluck();
	pStrings[index][1].pluck();
	pStrings[index][2].pluck();
    }
    
    public double ringNotes(){
	double sum = 0;
	for(int i = 0; i < pStrings.length;  i++){
	    sum+=pStrings[i][0].sample();
	    pStrings[i][0].tic();
	    sum+=pStrings[i][1].sample();
	    pStrings[i][1].tic();
	    sum+=pStrings[i][2].sample();
	    pStrings[i][2].tic();
	}
	return sum;
    }
			    

}
