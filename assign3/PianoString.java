/*
 * PianoString.java
 *
 */

package assign3;
import java.lang.Math;

public class PianoString extends InstString {
  
    public PianoString(double frequency) {
	r = new RingBuffer((int)Math.round(samplingRate/frequency));
	while(!r.isFull()){
	    r.enqueue(0);
	}
    }

    public PianoString(double[] init) {
	r = new RingBuffer(init.length);
	for(int i = 0; i < init.length; i++){
	    r.enqueue(init[i]);
	}
    }
   
    public void pluck() {
	int element;
	int oldSize = 0;
	while(!r.isEmpty()){
	    r.dequeue();
	    oldSize++;
	}
	while(!r.isFull()){
	    element = r.getSize();
	    if(element < ((7/16)*oldSize) || element > ((9/16)*oldSize)){
		r.enqueue(0);
	    }else{
		r.enqueue(.25*Math.sin(8*Math.PI*((element/oldSize)-(7/16))));
	    }
	}
    }
   
    public void tic() {	
	counter+=1;
	double firsterino = r.dequeue();
	double average = ((firsterino+sample())/2.0)*.996;
	r.enqueue(average);
    }

}
