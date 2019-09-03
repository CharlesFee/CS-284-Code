package CuckooHash;
public class testerino{
    public static void main(String []args){
	HashtableCuckoo<Integer, String> c = new HashtableCuckoo();
	c.put(7, "a");
	c.toString();
    }
}
