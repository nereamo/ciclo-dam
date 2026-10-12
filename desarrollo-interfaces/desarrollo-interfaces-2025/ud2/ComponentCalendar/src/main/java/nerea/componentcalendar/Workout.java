package nerea.componentcalendar;

/**
 * @author Nerea
 */
public class Workout {

    private int workoutId;
    private String userName;
    private String comments;
    private int numberOfExercises;

    public Workout(int workoutId, String userName, String comments, int numberOfExercises) {
        this.workoutId = workoutId;
        this.userName = userName;
        this.comments = comments;
        this.numberOfExercises = numberOfExercises;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    @Override
    public String toString() {
        return "WorkoutId: " + workoutId
                + ", User Name: " + userName
                + ", Comment: " + comments
                + "Number Exercises: " + numberOfExercises;
    }

}
