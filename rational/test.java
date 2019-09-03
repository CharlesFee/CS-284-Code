import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

 class Tests{
    
    @Test
    void testAddition(){
	RationalNumber a = new RationalNumber(10,12);
	RationalNumber b = new RationalNumber(1,3);
	RationalNumber ans = new RationalNumber(7,6);
	a.add(b);
	assertTrue(a.isEqual(ans));
    }
    @Test
    void testMultiply(){
	RationalNumber a = new RationalNumber(10,12);
	RationalNumber b = new RationalNumber(1,3);
	RationalNumber ans = new RationalNumber(5,18);
	a.multiply(b);
	assertTrue(a.isEqual(ans));
    }
   @Test
   void testDouble(){
       RationalNumber c = new RationalNumber(4,9);
	RationalNumber d = new RationalNumber(1,3);
	RationalNumber e = new RationalNumber(3,10);
	assertTrue(c.doubleValue()==0.444);
	assertTrue(d.doubleValue()==0.333);
	assertTrue(e.doubleValue()==0.3);
   }
   @Test
   void testFloat(){
       RationalNumber c = new RationalNumber(4,9);
	RationalNumber d = new RationalNumber(1,3);
	RationalNumber e = new RationalNumber(3,10);
	assertTrue(c.floatValue()==(float)0.444);
	assertTrue(d.floatValue()==(float)0.333);
	assertTrue(e.floatValue()==(float)0.3);
   }
   @Test
   void testInt(){
       RationalNumber c = new RationalNumber(6,5);
	RationalNumber d = new RationalNumber(15,7);
	RationalNumber e = new RationalNumber(3,10);
	assertTrue(c.intValue()==1);
	assertTrue(d.intValue()==2);
	assertTrue(e.intValue()==0);
   }
   @Test
   void testLong(){
       RationalNumber c = new RationalNumber(6,5);
	RationalNumber d = new RationalNumber(15,7);
	RationalNumber e = new RationalNumber(3,10);
	assertTrue(c.longValue()==(long)1);
	assertTrue(d.longValue()==(long)2);
	assertTrue(e.longValue()==(long)0);
   }
   @Test
   void testBin(){
       RationalNumber a = new RationalNumber("110.110_11");
       System.out.println(a.numerator);
       System.out.println(a.denominator);
       RationalNumber b = new RationalNumber("100.01_101");
       System.out.println(b.numerator);
       System.out.println(b.denominator);
   }
}
 
