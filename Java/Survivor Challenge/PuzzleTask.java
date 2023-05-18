// Jerry
// 01/25/2023
// CSE 123
// TA: GAURAV
// This class is a puzzle task that test if the player can get the correct answer 
// of a given question. The player can ask for some hints for the question.
// This class is the subclass of Task class, and superclass of BalanceTask class.

import java.util.*;

public class PuzzleTask extends Task {
    private String solution; // the solution of the task
    private List<String> hints; // a list of hints
    private boolean complete; // if the task is completed
    private int hintTrigger; // the times a player asked for a hint

    // construct the solution, the hints, and the description of a puzzle task
    // hints can be empty
    public PuzzleTask(String solution, List<String> hints, String description){
        super(description);
        this.solution = solution;
        this.hints = hints;
        this.complete = false;
        this.hintTrigger = 0;
    }

    // get the description of the task
    // return a string, that can be the hints if the player asked for it or only the description itself
    public String getDescription() {
        if (hintTrigger > 0 && hintTrigger <= hints.size()) {
            String hint = super.getDescription() + "\n" + "  HINT: " + hints.get(hintTrigger - 1);
            return hint;
        } else {
            return super.getDescription();
        }
    }

    // return a list of possible actions
    public List<String> getActionOptions(){
        String s1 = "hint";
        String s2 = "solve <solution>";
        List<String> option = new ArrayList<>();
        option.add(s1);
        option.add(s2);
        return option;
    }

    // to check if the task is completed
    // return true if it is completed, false otherwise
    public boolean isComplete() {
        return complete;
    }

    // to deal with the action inputed by the player
    // return true if the action is correct, can be a "hint" keyword or the correct solution
    // return false if all hints are given, or the solution is wrong
    // throw IllegalArgumentException for invalid input
    public boolean takeAction(String action){
        if (action.compareTo("hint") == 0){
            hintTrigger++;
            if (hintTrigger > hints.size()) {
                return false;
            }
            return true;
        }
        if (action.split(" ", 2)[0].compareTo("solve") == 0) {
            if (action.split(" ", 2)[1].compareTo(solution) == 0) {
                complete = true;
                return true;
            } else {
                return false;
            }
        }
        throw new IllegalArgumentException("**Invalid operation: " + action + "**");
    }

    // return hintTrigger
    public int getHintTrigger() {
        return hintTrigger;
    }

    // return hints
    public List<String> getHints() {
        return hints;
    }

    // set hintTrigger
    public void setHintTrigger(){
        hintTrigger++;
    }

    // set complete to true
    public void setComplete() {
        complete = true;
    }
}
