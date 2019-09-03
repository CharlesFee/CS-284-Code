/*
 * DrumString.java
 *
 */

package assign3;
import java.lang.Math;


public class DrumString extends InstString{

   
    public DrumString(double frequency) {
	r = new RingBuffer((int)Math.round(samplingRate/frequency));
	while(!r.isFull()){
	    r.enqueue(0);
	}
    }

    public DrumString(double[] init) {
	r = new RingBuffer(init.length);
	for(int i = 0; i < init.length; i++){
	    r.enqueue(init[i]);
	}
    }

    public void pluck() {
	int dabble = 0;
	while(!r.isEmpty()){
	    r.dequeue();
	    dabble++;
	}
	while(!r.isFull()){
	    if(Math.sin(1/dabble) > 0){
		r.enqueue(1);
	    }else{
		r.enqueue(-1);
	    }
	}
    }
    
    public void tic() {
	counter+=1;
	double s1 = r.dequeue();
	double s2 = r.peek();
	double choose = Math.random();
	double negation = Math.random();
	double answer = 0;
	if(choose <= .4){
	    answer = s1;
	}else{
	    answer = (s1+s2)/2;
	}
	if(negation > .5){
	    answer = -answer;
	}
	r.enqueue(answer);
    }
}
