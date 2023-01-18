import java.util.List;
import java.util.Random;

/**
 * Agent class represents an Agent that randomly places two unique
 * ingredients on a table
 *
 * @author Oritsemeyiwa Jordan Temile
 */
public class Agent extends Thread {
    private final String agentName;
    private final List<String> table;
    private final String[] ingredients;

    /**
     * Constructor
     * @param agentName     Name of thread
     * @param table         The table ingredients will be placed on
     * @param ingredients   List of all items required to make a sandwich
     */
    public Agent(String agentName, List<String> table, String[] ingredients) {
        super(agentName);
        this.agentName = agentName;
        this.table = table;
        this.ingredients = ingredients;
    }

    /**
     * Randomly selects two ingredients to be placed on table if empty
     * @throws InterruptedException
     */
    public void placeIngredient() throws InterruptedException {
        synchronized(table) {
            while(table.size()!=0) {
                table.wait();
            }

            Random random = new Random();
            while(table.size()!=2) {
                int tempInt = random.nextInt(3);
                if(table.contains(ingredients[tempInt])) {
                    continue;
                }
                table.add(ingredients[tempInt]);
            }
            System.out.println(agentName + " Placed ingredients on the table");
            table.notifyAll();
        }
    }

    /**
     * Override run method for threads
     * @see java.lang.Thread
     */
    @Override
    public void run() {
        while(Kitchen.sandwichNo<20) {
            try {
                placeIngredient();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
