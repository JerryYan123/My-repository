// Jerry
// 01/25/2023
// CSE 123
// TA: GAURAV
// The client class finds the most beneficial way to help people from
// different location in limited budget
import java.util.*;

public class Client {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // List<Location> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Location> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Set<Location> allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

    // Takes in the budget, a list of location that needs to be helped
    // Find the most beneficial way to help people from different location
    // The in the budget, the method will find the combination that most amount 
    // of the people will be help, and within the lowest cost with same amount of 
    // people in different combinations
    // Return a set of location that are allocated depends on the budget
    public static Set<Location> allocateRelief(double budget, List<Location> sites) {
        Set<Location> result = new HashSet<Location>();
        return allocateRelief(budget, sites, result);
    }

    // private helper method by using recursion to find the best combination
    private static Set<Location> allocateRelief(double budget, List<Location> sites, Set<Location> soFar) {
        if (budget == 0 || sites.isEmpty()) {
            return soFar;
        }
        Set<Location> currBest = new HashSet<Location>(soFar);
        for (int i = 0; i < sites.size(); i++) {
            
            Location site = sites.get(i);
            if (site.getCost() <= budget) {
                sites.remove(i);
                soFar.add(site);

                Set<Location> result = allocateRelief(budget - site.getCost(), sites, soFar);

                if (Location.countPeople(currBest) < Location.countPeople(result)
                    || (Location.countPeople(currBest) == Location.countPeople(result)
                    && Location.totalCost(currBest) > Location.totalCost(result))) {
                    currBest = new HashSet<Location>(result);
                }

                sites.add(i, site);
                soFar.remove(site);
            }
        }
        return currBest;
    }

    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!**

    public static void printResult(Set<Location> outcome, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + outcome);
        System.out.println("  People saved: " + Location.countPeople(outcome));
        System.out.printf("  Cost: $%.2f\n", Location.totalCost(outcome));
        System.out.printf("  Unused budget: $%.2f\n", (budget - Location.totalCost(outcome)));
    }

    public static List<Location> createRandomScenario(int numLocs, int minPop, int maxPop, double minCostPer, double maxCostPer) {
        List<Location> result = new ArrayList<>();

        for (int i = 0; i < numLocs; i++) {
            int pop = rand.nextInt(minPop, maxPop + 1);
            double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Location("Location #" + i, pop, round2(cost)));
        }

        return result;
    }

    public static List<Location> createSimpleScenario() {
        List<Location> result = new ArrayList<>();

        result.add(new Location("Location #1", 50, 500));
        result.add(new Location("Location #2", 100, 700));
        result.add(new Location("Location #3", 60, 1000));
        result.add(new Location("Location #4", 20, 1000));
        result.add(new Location("Location #5", 200, 900));

        return result;
    }    

    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
