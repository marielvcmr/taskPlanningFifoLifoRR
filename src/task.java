public class task
{
    String name;
    int ti;
    int t;
    int tcopy;
    int tf;
    int T;
    int Espera;
    double Servicio;
    int startClock;
    boolean done;

    task(String name, int ti, int t)
    {
        this.name = name;
        this.ti = ti;
        this.t = t;
        this.tcopy = t;
        this.done = false;
    }

    // prints all tasks data excepting the clk time
    public void printCompletedTask()
    {
        System.out.println("Task: " + name + ", ti = " +  ti + ", t = " + t + ",  tf = " +  tf +
                ", T = "+ T+ ", Espera = " + Espera + ", Servicio = " + Servicio );
    }

    // prints the task data given by the user
    public void printIncompleteTask()
    {
        System.out.println("Task: " + name + ", ti = " +  ti + ", t = " + t);
    }
}
