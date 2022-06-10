import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BacktrackingAVLTest {
    BacktrackingAVL tree = new BacktrackingAVL();

    @Before
    public void setUp() throws Exception {
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
        tree.insert(100);
        tree.insert(120);
        tree.insert(140);
        tree.insert(160);
        tree.insert(180);
        tree.insert(200);


    }

    @Test
    public void BacktrackTest() {
        tree.insert(10);

        for (int i = 0; i < 9; i++) {
            System.out.println("Before Backtrack");
            tree.printTree();

            tree.Backtrack();
            System.out.println("After Backtrack");
            tree.printTree();
        }

    }

    @Test
    public void SelectTest() {
        tree.printTree();
        for (int i = 1; i < 10; i++) {
            Assert.assertEquals("Testing Select on " + 20*i, 20*i, tree.Select(i));
        }
    }

    @Test
    public void RankTest() {
        tree.printTree();
        Assert.assertEquals("Testing Rank on " + 10, 0,  tree.Rank(10)  );
        Assert.assertEquals("Testing Rank on " + 20, 0,  tree.Rank(20)  );
        Assert.assertEquals("Testing Rank on " + 30, 1,  tree.Rank(30)  );
        Assert.assertEquals("Testing Rank on " + 40, 1,  tree.Rank(40)  );
        Assert.assertEquals("Testing Rank on " + 50, 2,  tree.Rank(50)  );
        Assert.assertEquals("Testing Rank on " + 60, 2,  tree.Rank(60)  );
        Assert.assertEquals("Testing Rank on " + 70, 3,  tree.Rank(70)  );
        Assert.assertEquals("Testing Rank on " + 201, 10,  tree.Rank(201)  );
        Assert.assertEquals("Testing Rank on " + 140, 6,  tree.Rank(140)  );
        Assert.assertEquals("Testing Rank on " + 80, 3,  tree.Rank(80)  );


    }
}