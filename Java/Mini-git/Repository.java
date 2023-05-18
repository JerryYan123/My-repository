// Jerry
// 01/25/2023
// CSE 123
// TA: GAURAV
// This Repository class has a inner Commit class, which represent each commit 
// made by the user. The repository includes multiple functions to modify the 
// commit.
import java.util.*;

public class Repository {
    
    private Commit head;
    private String name;

    // construct a new, empty repository with the given name
    // if the name is null or empty, throw IllegalArgumentException
    public Repository(String name) {
        if (name == null || name == "") {
            throw new IllegalArgumentException("The name is null!");
        }
        this.name = name;
    }

    // return the id of the current head of this repository
    // return null if the head is null
    public String getRepoHead() {
        if (this.head == null) {
            return null;
        }
        return head.id;
    }

    // return a formatted string representing the repository
    // if the head is null, return a string showing there is no commit
    public String toString() {
        if (this.head == null) {
            return this.name + " - No commits";
        }
        return this.name + " - Current head: " + this.head.toString();
    }

    // Take a int n representing the recent n commits
    // Return a string consisted of these, with the recent first
    // Return all commits if there are less than n commits, a empty string 
    // if there is no commits, throw IllegalArgumentException is n is non-positive
    public String getHistory(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid number for history!");
        }
        String result = "";
        Commit temp = this.head;
        if (temp == null) {
            return result;
        }
        while(temp.past != null && n != 1) {
            result += temp.toString() + "\n";
            temp = temp.past;
            n--;
        }
        /*if (temp.past == null) {
            result = result.substring(0, result.length() - 1);
            return result;
        }*/
        // temp.past is null, temp.past.tostring error?
        result += temp.toString();
        return result;
    }

    // Takes a string message
    // Create a commit with the given message and 
    // add it into the repository
    // Return the id of the current head
    public String commit(String message) {
        this.head = new Commit(message, this.head);
        return this.head.id; 
    }

    // Take an int n indicating n commits in the past
    // Reset the head of the repository to the n commits
    // Reset the head to be null if there are less commits than n, 
    // throw IllegalArgumentException if n is non-positive
    public void reset(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid number for reset!");
        }
        while (this.head != null && n != 0) {
            this.head = this.head.past;
            n--;
        }

    }

    // Take a targetId of the target dropped commit
    // Remove the commit with the targetId in the repository
    // Return the id of the dropped commit
    // Return null if there is no commit with targetId or there are no 
    // commits in the repository
    public String drop(String targetId) {
        if (this.head == null) {
            return null;
        }
        Commit temp = this.head;
        if (temp.id == targetId) {
            this.head = temp.past;
            return targetId;
        }
        while (temp.past != null) {
            if (temp.past.id == targetId) {
                temp.past = temp.past.past;
                return targetId;
            }
            temp = temp.past;
        }
        return null;
    }

    // Take a targetId of the targeted squash commit 
    // Create a new commit to represent the target commit and the one before it
    // The new commit has a new id with the message combined with the target message
    // and the one before it.
    // Return the id of the earlier two squashed commit
    // Return null if there is no commit with targeted id or
    // if there is no commit before the target commit, no changes will be made
    public String squash(String targetId) {
        Commit temp = this.head;
        if (temp.id == targetId) {
            if (temp.past == null) {
                return null;
            }
            String data = temp.past.id;
            String newCommit = temp.message;
            String oldCommit = temp.past.message;
            Commit squash = new Commit("SQUASHED: " + newCommit + "/" + oldCommit, temp.past.past);
            this.head = squash;
            return data;
        }
        while(temp.past.id != targetId) {
            temp = temp.past;
            if(temp.past.past == null) {
                return null;
            }
        }
        if (temp.past.past == null) {
            return null;
        }
        String data = temp.past.past.id;
        String newCommit = temp.past.message;
        String oldCommit = temp.past.past.message;
        Commit squash = new Commit("SQUASHED: " + newCommit + "/" + oldCommit, temp.past.past.past);
        temp.past = squash;
        return data;
    }

    // Commit class for single commit in the repository
    public static class Commit {
        public String id;
        public String message;
        public Commit past;

        public Commit(String message, Commit past) {
            this.id = getNewId();
            this.message = message;
            this.past = past;
        }
    
        public Commit(String message) {
            this(message, null);
        }

        public String toString() {
            return id + ": " + message;
        }

        private static String getNewId() {
            return UUID.randomUUID().toString();
        }
    }
}
