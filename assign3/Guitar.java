/*
 * Guitar.java
 */
package assign3;

public class Guitar extends Instrument{


    public Guitar(int numNotes){
	strings = new InstString[numNotes];
	for(int i = 0; i < strings.length; i++){
	    strings[i] = new GuitarString((double)440*Math.pow(2,(i-24)/12.0));
	}
    }		    
}
