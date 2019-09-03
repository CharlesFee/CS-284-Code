/*
 * GuitarString.java
 *
 */

package assign3;
import java.lang.Math;

public class GuitarString extends InstString{
   
    public GuitarString(){};
   
 
    public GuitarString(double frequency) {	
	r = new RingBuffer((int)Math.round(samplingRate/frequency));
	while(!r.isFull()){
	    r.enqueue(0);
	}
    }

    public GuitarString(double[] init) {
	r = new RingBuffer(init.length);
	for(int i = 0; i < init.length; i++){
	    r.enqueue(init[i]);
	}
    }
   
    public void pluck() {
	double wumbology = 0;
	while(false == r.isEmpty()){
	    r.dequeue();
	}
	while(false == r.isFull()){
	    wumbology = Math.random()-.5;
	    r.enqueue(wumbology);
	}
    }
   
    public void tic() {
	counter+=1;
	double firsterino = r.dequeue();
	double average = ((firsterino+sample())/2.0)*.996;
	r.enqueue(average);
    }

}
