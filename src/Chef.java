import java.util.List;

/**
 * Chef class represents a Chef with a final, infinite ingredient to make a sandwich
 * with ingredients on a table
 *
 * @author Oritsemeyiwa Jordan Temile
 */
public class Chef extends Thread {
    private final List<String> table;
    private final String chefName;
    private final String ingredient;

    /**
     * Constructor
     * @param chefName      Name of the thread
     * @param table         The table ingredients will be obtained from
     * @param ingredient    Final ingredient
     */
    public Chef(String chefName, List<String> table, String ingredient) {
        super(chefName);
        this.table = table;
        this.chefName = chefName;
        this.ingredient = ingredient;
    }

    /**
     * Make sandwich with ingredients if remaining ingredients
     * are available
     * @throws InterruptedException
     */
    public void makeSandwich() throws InterruptedException {
        synchronized(table) {
            while(table.size()!=2 || table.contains(ingredient)) {
                table.wait();
            }
            System.out.println(chefName + " made and ate a sandwich");
            Kitchen.sandwichNo++;
            table.clear();
            table.notifyAll();
        }
    }

    /**
     * Override run method for threads
     * @see java.lang.Thread
     */
    @Override
    public void run() {
        while(Kitchen.sandwichNo <= 20) {
            try {
                makeSandwich();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
