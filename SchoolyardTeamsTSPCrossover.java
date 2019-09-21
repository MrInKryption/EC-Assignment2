import java.util.ArrayList;
import java.util.Random;

// Performs random crossover on the two parents.
// The two "children" take turns choosing an individual from both parents that they want
// They always try to take the city who is closest to any of their cities.
// After child 1 has chosen its first city, child 2 chooses its first, etc
// This way we shouldn't end up with a "hard" and "easy" child and instead two equally difficult ones
public class SchoolyardTeamsTSPCrossover implements ITSPCrossover
{
    public ArrayList<TSP_Instance> crossover(TSP_Instance first_parent, TSP_Instance second_parent)
    {

        // Get a list of all the city points
        ArrayList<Point> remaining_kids  = new ArrayList<Point>();
        
        remaining_kids.addAll(first_parent.getCoordinates());
        remaining_kids.addAll(second_parent.getCoordinates());
        Random rand = RandomNumberGenerator.getRandom();
        TSP_Instance instance = new TSP_Instance();
        boolean turn = true;    //true is team_1, false is team_2
        int choice;

        // Initialise the teams
        ArrayList<Point> team_1 = new ArrayList<Point>();
        ArrayList<Point> team_2 = new ArrayList<Point>();

        // Choose the initial team leaders
        for(int i = 0; i < 2; i++){
            choice = rand.nextInt(remaining_kids.size());
            if(turn){
                team_1.add(remaining_kids.get(choice));
            }
            else{
                team_2.add(remaining_kids.get(choice));
            }
            remaining_kids.remove(choice);
            turn = !turn;
        }

        // Pick the rest of the kids
        int length = remaining_kids.size();
        double distance, current_distance;
        int team_size;
        for(int i = 0; i < length; i++){
            //Set here incase somehow a suitable teammate isn't found
            choice = 0;
            distance = -1;

            // Figure out which child to choose. It should be the child who is closest
            // distance wise to any of the kids from that team
            team_size = (turn) ? team_1.size() : team_2.size();
            for(int j = 0; j < team_size; j++){
                for(int k = 0; k < remaining_kids.size(); k++){
                    current_distance = instance.getDistance(((turn) ? team_1.get(j) : team_2.get(j)), remaining_kids.get(k));

                    if(distance >= 0 && current_distance < distance){
                        distance = current_distance;
                        choice = k;
                    }
                }
            }

            // Remove that kid from the pool (He can't swim yet)
            if(turn){
                team_1.add(remaining_kids.get(choice));
            }
            else{
                team_2.add(remaining_kids.get(choice));
            }
            remaining_kids.remove(choice);
            turn = !turn;
        }

        // Place the children in an arraylist and return them.
        ArrayList<TSP_Instance> finalResult = new ArrayList<TSP_Instance>();
        finalResult.add(new TSP_Instance(team_1));
        finalResult.add(new TSP_Instance(team_2));
        
        return finalResult;
    }
}
