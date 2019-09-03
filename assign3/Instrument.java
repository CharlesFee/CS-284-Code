/*
 * Instrument.java
 */
package assign3;

public abstract class Instrument {
    
    protected  InstString[] strings;
    
    public void playNote(int i){
	strings[i].pluck();
    }

    public  double ringNotes(){
	double sum = 0;
	for(int i = 0; i < strings.length;  i++){
	    strings[i].tic();
	    sum+=strings[i].sample();
	}
	return sum;
    }

}
