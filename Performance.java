import java.util.ArrayList;


public class Performance
{
    InverOver inver; //inverOver algorithm

    //Populations for - comparison
    ArrayList<TSP_Instance> inver_2op_1;  //population for inver vs 2op
    ArrayList<TSP_Instance> inver_alg_1;  //population for inver vs our algorithm
    ArrayList<TSP_Instance> alg_2op_1;    //population for our alg vs 2op

    //Populations for / comparison
    ArrayList<TSP_Instance> inver_2op_2;  //population for inver vs 2op
    ArrayList<TSP_Instance> inver_alg_2;  //population for inver vs our algorithm
    ArrayList<TSP_Instance> alg_2op_2;    //population for our alg vs 2op


    public Performance(int problem_size, double min, double max, int population_size)
    {
        for(int i = 0; i < population_size; i++)
        {
            TSP_Instance new_tsp = new TSP_Instance(problem_size, min, max);

            inver_2op_1.add(new_tsp);
            inver_alg_1.add(new_tsp);
            alg_2op_1.add(new_tsp);

            inver_2op_2.add(new_tsp);
            inver_alg_2.add(new_tsp);
            alg_2op_2.add(new_tsp);
        }
        

    }

    //not sure what I am doing here
    //a runnable function interface should allow the first and second tasks in 3
    public static void main(String[] args) {
		
		Runnable first = () -> {
			//run the  - comparison
        };
        
        Runnable second = () -> {
            //run the / comparison
        };
		
		Thread t1 = new Thread(first);
		Thread t2 = new Thread(second);
		
		t1.start();
		t2.start();
	}
}