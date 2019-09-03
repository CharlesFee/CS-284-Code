/*
 * InstString.java
 *
 */

package assign3;

public abstract class InstString{
    protected RingBuffer r;
    protected int counter;
    protected int samplingRate = 44100;
    /* To be implemented by subclasses*/
    public abstract void pluck();
    public abstract void tic();

    public double sample(){
	return r.peek();
    }

    public int time(){
	return counter;
    }

}
