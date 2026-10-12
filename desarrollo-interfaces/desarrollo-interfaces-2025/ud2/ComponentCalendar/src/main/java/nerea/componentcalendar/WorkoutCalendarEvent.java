
package nerea.componentcalendar;

import java.util.EventObject;
import java.util.List;

/**
 * @author Nerea
 */
public class WorkoutCalendarEvent extends EventObject {

    private List<Workout> workouts; // Lista de workouts

    public WorkoutCalendarEvent(Object source, List<Workout> workouts) {
        super(source);
        this.workouts = workouts;

    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    @Override
    public String toString() {
        String workoutInfo = "";
        for (Workout workout : workouts) {
            workoutInfo += "Workout Id: " + workout.getWorkoutId() + "\n";
            workoutInfo += "User Name: " + workout.getUserName() + "\n";
            workoutInfo += "Comments: " + workout.getComments() + "\n";
            workoutInfo += "Number of Exercises: " + workout.getNumberOfExercises() + "\n\n";

        }
        return workoutInfo;
    }

}
