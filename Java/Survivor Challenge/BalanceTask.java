// Jerry
// 01/25/2023
// CSE 123
// TA: GAURAV
// This class is a balance task that let the player to move the objects to reach the balance. 
// There are some hints provided to help the player. It is the subclass of PuzzleTask class.
import java.util.*;

public class BalanceTask extends PuzzleTask {
    private List<String> solution; // solution of the question

    // construct the solution, hints, and description
    // hints can be empty
    public BalanceTask(List<String> solution, List<String> hints, String description) {
        super(null, hints, description);
        this.solution = solution;
    }

    // return a list of possible actions
    public List<String> getActionOptions(){
        String s1 = "hint";
        String s2 = "solve <leftsidemove> <rightsidemove>";
        List<String> option = new ArrayList<>();
        option.add(s1);
        option.add(s2);
        return option;
    }

    // to deal with the action inputed by the player
    // return true if the action is correct, can be a "hint" keyword or the correct solution
    // return false if all hints are given, or the solution is wrong
    // throw IllegalArgumentException for invalid input
    public boolean takeAction(String action){
        if (action.compareTo("hint") == 0){
            getHintTrigger();
            setHintTrigger();
            if (getHintTrigger() > super.getHints().size()) {
                return false;
            }

            return true;
        }
        if (action.split(" ", 3)[0].compareTo("solve") == 0) {
            if (action.split(" ", 3)[1].compareTo(solution.get(0)) == 0 
                && action.split(" ", 3)[2].compareTo(solution.get(1)) == 0) {
                setComplete();
                return true;
            } else {
                return false;
            }
        }
        throw new IllegalArgumentException("**Invalid operation: " + action + "**");
    }

}
