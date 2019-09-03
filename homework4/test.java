package homework4;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 * @author Charles Fee
 * I pledge my honor that I have abided by the Stevens Honors System
 */
class Tests{

	@Test
    void testAdd(){
	Treap<Integer> testTree = new Treap<Integer>();
	testTree.add(4,81);
	testTree.add(2,69);
	testTree.add(6,30);
	testTree.add(1,16);
	testTree.add(3,88);
	testTree.add(5,17);
	testTree.add(7,74);
	String ans = "(key=1, priority=16)\n\tnull\n\t(key=5, priority=17)\n\t\t(key=2, priority=69)\n\t\t\tnull\n\t\t\t(key=4, priority=81)\n\t\t\t\t(key=3, priority=88)\n\t\t\t\t\tnull\n\t\t\t\t\tnull\n\t\t\t\tnull\n\t\t(key=6, priority=30)\n\t\t\tnull\n\t\t\t(key=7, priority=74)\n\t\t\t\tnull\n\t\t\t\tnull\n";
	assertTrue(testTree.toString().equals(ans));
	}

	@Test
	void testDel(){
	Treap<Integer> testTree = new Treap<Integer>();
	testTree.add(4,81);
	testTree.add(2,69);
	testTree.add(6,30);
	testTree.add(1,16);
	testTree.add(3,88);
	testTree.add(5,17);
	testTree.add(7,74);
	testTree.delete(5);
	String ans = "(key=1, priority=16)\n\tnull\n\t(key=6, priority=30)\n\t\t(key=2, priority=69)\n\t\t\tnull\n\t\t\t(key=4, priority=81)\n\t\t\t\t(key=3, priority=88)\n\t\t\t\t\tnull\n\t\t\t\t\tnull\n\t\t\t\tnull\n\t\t(key=7, priority=74)\n\t\t\tnull\n\t\t\tnull\n";
	assertTrue(testTree.toString().equals(ans));
	}

	@Test
	void testFind(){
	Treap<Integer> testTree = new Treap<Integer>();
	testTree.add(4,81);
	testTree.add(2,69);
	testTree.add(6,30);
	testTree.add(1,16);
	testTree.add(3,88);
	testTree.add(5,17);
	testTree.add(7,74);
	assertTrue(testTree.find(5) == 5);
	assertTrue(testTree.find(23) == null);
	}

}
