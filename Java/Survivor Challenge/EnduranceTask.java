// Jerry
// 01/25/2023
// CSE 123
// TA: GAURAV
// This class is a endurance task that test if the player can type in the 
// correct actions for the correct times for a question. It is the subclass of Task.

import java.util.*;

public class EnduranceTask extends Task{
    private String type; // the type of task need to complete the task
    private int duration; // the times of the action need to be taken
    private List<String> actions; // the possible actions

    // construct a question with description, the target type, and the duration
    public EnduranceTask(String type, int duration, String description) {
        super(description);
        this.type = type;
        this.duration = duration;
        String a[] = new String[] {"jump", "run", "swim", "crawl", "climb"};
        this.actions = Arrays.asList(a);
    }

    // return a list of possible actions
    public List<String> getActionOptions(){
        return actions;
    }

    // the method test if the task is completed
    // return true if it is completed, false otherwise
    public boolean isComplete(){
        if (duration <= 0){
            return true;
        } else {
            return false;
        }
    }

    // to judge the action inputed by the player
    // return true if the action is correct, false otherwise
    // throw IllegalArgumentException when the action is Invalid
    public boolean takeAction(String action){
        if (!getActionOptions().contains(action)) {
            throw new IllegalArgumentException("The action attempted is not a valid action!");
        }
        if (action.compareTo(type) == 0) {
            duration--;
            return true;
        } else {
            return false;
        }
    }
}
