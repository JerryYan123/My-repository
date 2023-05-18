// Jerry
// 01/25/2023
// CSE 123
// TA: GAURAV
// this class is a precision task that test if the player can type in the correct 
// action according to the description. It is the subclass of Task class.

import java.util.*;
public class PrecisionTask extends Task{
    private List<String> requiredActions; // the required actions to complete the task
    private int location; // the progress of the task
    private List<String> actions; // the possible actions

    // construct the question with description and the answer of a task
    public PrecisionTask(List<String> requiredActions, String description){
        super(description);
        this.requiredActions = requiredActions;
        this.location = 0;
        String a[] = new String[] {"jump", "run", "swim", "crawl", "climb"};
        this.actions = Arrays.asList(a);
    }

    // return a list of possible actions
    public List<String> getActionOptions(){
        return actions;
    }

    // to check if the task is completed
    // return true if it is completed, false otherwise
    public boolean isComplete(){
        if (location == requiredActions.size()){
            return true;
        } else {
            return false;
        }
    }

    // to judge the action inputed by the player
    // the action should be typed in a correct order
    // return true if the current action is correct, false otherwise
    // throw IllegalArgumentException when the action is Invalid
    public boolean takeAction(String action){
        if (!getActionOptions().contains(action)) {
            throw new IllegalArgumentException("The action attempted is not a valid action!");
        }
        if (action.equals(requiredActions.get(location))) {
            location++;
            return true;
        } else {
            return false;
        }
    }
}
