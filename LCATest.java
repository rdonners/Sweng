import static org.junit.Assert.*;
import org.junit.Test;

public class LCATest {

	@Test
	public void testProperVals() {
		
		LCA tree = new LCA();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);

    	assertEquals(" test", 2,  tree.findLCA(2,4));
    	assertEquals(" test", 1,  tree.findLCA(3,4));
    	assertEquals(" test", 1,  tree.findLCA(4,6));
    	assertEquals(" test", 2,  tree.findLCA(4,5));
	}
	@Test
	public void singleElementLCA()
	{
		LCA tree = new LCA();
		tree.root = new Node(1);
		assertEquals("SingleElement Tree LCA", 1, tree.findLCA(1,1));

		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);	
	}
	
	

}
