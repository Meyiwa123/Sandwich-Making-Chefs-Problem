import java.util.List;
import java.util.ArrayList;
import java.util.Collections;



/**
 * Kitchen class represents a Kitchen that contains a table where
 * ingredients are shared among agents and chefs.
 *
 * @author Oritsemeyiwa Jordan Temile
 */
public class Kitchen {
    public List<String> table;
    public static int sandwichNo;

    /**
     * Constructor, initializes the table and ingredient list, and starts
     * the thread
     */
    public Kitchen() {
        sandwichNo = 0;
        table = Collections.synchronizedList(new ArrayList<String>());
        String[] ingredients = {"bread", "peanut butter", "jam"};

        Thread agent = new Agent("agent", table, ingredients);
        Thread chef1 = new Chef("chef1", table, ingredients[0]);
        Thread chef2 = new Chef("chef2", table, ingredients[1]);
        Thread chef3 = new Chef("chef3", table, ingredients[2]);

        agent.start();
        chef1.start();
        chef2.start();
        chef3.start();
    }

    /**
     * Main method to run the program
     */
    public static void main(String[] args) {
        new Kitchen();
    }
}