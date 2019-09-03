package util;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


class CalcTests{
    private Calculator calc = new Calculator();

    @Test
    void additionTest(){
	assertEquals(2, calc.add(1,1));
    }

    @Test
    void latestTest(){
	calc.add(1,1);
	assertEquals(2, calc.getLastResult());
    }
}
