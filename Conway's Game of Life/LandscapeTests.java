import java.util.ArrayList;

public class LandscapeTests {

    public static void landscapeTests() {

        // case 1: testing Landscape(int, int)

        {
            System.out.println("TEST 1");
            // set up
            Landscape l1 = new Landscape(2, 4);
            Landscape l2 = new Landscape(10, 10);

            // verify
            System.out.println(l1);
            System.out.println("\n");
            System.out.println(l2);

            // test
            assert l1 != null : "Error in Landscape::Landscape(int, int)";
            assert l2 != null : "Error in Landscape::Landscape(int, int)";
            System.out.println();
        }

        // case 2: testing reset()
        {
            System.out.println("TEST 2");
            Landscape l1 = new Landscape(4, 4);
            System.out.println(l1);
            l1.reset();
            System.out.println(l1);
            System.out.println();
        }

        // case 3: testing getRows()
        {
            System.out.println("TEST 3");
            Landscape l1 = new Landscape(4, 5);
            
            // verify
            System.out.println(l1);
            System.out.println(l1.getRows());


            // test
            assert 4 == l1.getRows() : "Error in getRows()";
            System.out.println();

        }

        // case 4: testing getCols()
        {
            System.out.println("TEST 4");
            Landscape l1 = new Landscape(4, 9);
            
            // verify
            System.out.println(l1);
            System.out.println(l1.getCols());


            // test
            assert 4 == l1.getCols() : "Error in getCols()";
            System.out.println();

        }

        // case 5: testing getCell(int, int)
        {
            System.out.println("TEST 5");
            Landscape l1 = new Landscape(4, 9, 50);
  
            // verify
            System.out.println(l1);
            System.out.println(l1.getCell(2,2));

            // test
            // assert l1[2][2] == l1.getCell(2,2) : "Error in getCols()";
            System.out.println();

        }

        // case 6: testing getNeighbors()
        
        {
            System.out.println("Test 6");
            Landscape ls = new Landscape(4, 4, 25);        
            System.out.println(ls);
            ArrayList<Cell> neigh = ls.getNeighbors(1, 3);
            System.out.println(neigh);
            System.out.println();

        }

        // case 7: testing advance()
        {
            // set up
            System.out.println("Test 7");
            Landscape ls = new Landscape(5, 5, 50); 
            System.out.println(ls);
            ls.advance();
            System.out.println(ls);
            System.out.println();

            // verify


            // test

        }
    }


    public static void main(String[] args) {

        landscapeTests();
    }
}