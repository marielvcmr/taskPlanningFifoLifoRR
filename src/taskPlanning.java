import java.util.ArrayList;
import java.util.Scanner;

public class taskPlanning
{

    ArrayList<task> givenTasks = new ArrayList<>();
    int currentClk;
    int index;
    int numTasks;
    int completedTasks;
    int quantum;
    boolean qRound;
    double totalFIFO;
    double esperaFIFO;
    double servicioFIFO;
    double duracionFIFO;
    double totalLIFO;
    double esperaLIFO;
    double servicioLIFO;
    double duracionLIFO;
    double totalRR;
    double esperaRR;
    double servicioRR;
    double duracionRR;

    public void askTasks()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of tasks: ");
        numTasks = input.nextInt();
        for(int i = 0; i < numTasks; i++)
        {
            System.out.print("Enter name for task #" + (i+1) + ": ");
            String name = input.next();
            System.out.print("Enter initial time for task " + name + ": ");
            int initialTime = input.nextInt();
            System.out.print("Enter time for task " + name + ": ");
            int time = input.nextInt();
            task task = new task(name, initialTime, time);
            givenTasks.add(task);
        }
    }

    public void setVariablesForFIFO()
    {
        this.currentClk = 0;
        this.index = 0;
        // numTasks
        this.completedTasks = 0;
    }

    public void setVariablesForLIFO()
    {
        this.currentClk = 0;
        this.index = this.numTasks - 1;
        // numTasks
        this.completedTasks = 0;
    }

    public void setVariablesForRoundRobin()
    {
        this.currentClk = 0;
        this.index = 0;
        // numTasks
        this.completedTasks = 0;
        this.qRound = false;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the quatum value: ");
        this.quantum = input.nextInt();
    }

    public void setTasksAsDefault()
    {
        for(int i = 0; i < numTasks; i++)
        {
            givenTasks.get(i).tf = 0;
            givenTasks.get(i).T = 0;
            givenTasks.get(i).Espera = 0;
            givenTasks.get(i).Servicio = 0;
            givenTasks.get(i).done = false;
            givenTasks.get(i).startClock = 0;

        }
    }

    public double getAverageT()
    {
        double sum = 0;
        for(int i = 0; i < numTasks; i++)
        {
            sum = sum + givenTasks.get(i).T;
        }
        return (sum / numTasks);
    }

    public double getAverageEspera()
    {
        double sum = 0;
        for(int i = 0; i < numTasks; i++)
        {
            sum = sum + givenTasks.get(i).Espera;
        }
        return (sum / numTasks);
    }

    public double getAverageServicio()
    {
        double sum = 0;
        for(int i = 0; i < numTasks; i++)
        {
            sum = sum + givenTasks.get(i).Servicio;
        }
        return (sum / numTasks);
    }

    public void printGivenTasks()
    {
        for(int i = 0; i < numTasks; i++)
        {
            givenTasks.get(i).printIncompleteTask();
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public void printProcessedTasks()
    {
        for(int i = 0; i < numTasks; i++)
        {
            givenTasks.get(i).printCompletedTask();
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public void processTasksFIFO()
    {
        System.out.println("Processing Tasks through FIFO...");
        long inicio = System.nanoTime();
        while(completedTasks < numTasks)
        {
            if(index <= numTasks - 1)
            {
                if(!givenTasks.get(index).done)
                {
                    if(givenTasks.get(index).ti <= currentClk)
                    {
                        // do task
                        givenTasks.get(index).startClock = currentClk;
                        givenTasks.get(index).tf = givenTasks.get(index).startClock + givenTasks.get(index).t;
                        givenTasks.get(index).T = givenTasks.get(index).tf - givenTasks.get(index).ti;
                        givenTasks.get(index).Espera = givenTasks.get(index).T - givenTasks.get(index).t;
                        givenTasks.get(index).Servicio = round3((double) givenTasks.get(index).t / givenTasks.get(index).T);
                        givenTasks.get(index).done = true;
                        currentClk =  givenTasks.get(index).tf;
                        completedTasks++;
                        index = 0;
                    }
                    else
                    {
                        index = index + 1;
                        if(index == numTasks)
                        {
                            currentClk++;
                        }
                        continue;
                    }
                }
                else
                {
                    index = index + 1;
                    continue;
                }

            }
            else
            {
                index = 0;
                continue;
            }
        }
        long fin = System.nanoTime(); // tiempo final
        // calculo de
        //duracion algoritmo en milisegundos
        duracionFIFO = round3((fin - inicio)/ 1_000_000.0);
        //  double totalFIFO;
        totalFIFO = round3(getAverageT());
        //    double esperaFIFO;
        esperaFIFO = round3(getAverageEspera());
        //    double servicioFIFO;
        servicioFIFO = round3(getAverageServicio());
        System.out.println("Finished processing Tasks through FIFO...");
        System.out.println("Results for each task in FIFO: ");
        printProcessedTasks();
    }

    public void processTasksLIFO()
    {
        System.out.println("Processing Tasks through LIFO...");
        long inicio = System.nanoTime();
        while(completedTasks < numTasks)
        {
            if(index >= 0)
            {
                if(!givenTasks.get(index).done)
                {
                    if(givenTasks.get(index).ti <= currentClk)
                    {
                        // do task
                        givenTasks.get(index).startClock = currentClk;
                        givenTasks.get(index).tf = givenTasks.get(index).startClock + givenTasks.get(index).t;
                        givenTasks.get(index).T = givenTasks.get(index).tf - givenTasks.get(index).ti;
                        givenTasks.get(index).Espera = givenTasks.get(index).T - givenTasks.get(index).t;
                        givenTasks.get(index).Servicio = round3((double) givenTasks.get(index).t / givenTasks.get(index).T);
                        givenTasks.get(index).done = true;
                        currentClk =  givenTasks.get(index).tf;
                        completedTasks++;
                        index = numTasks - 1;
                    }
                    else
                    {
                        index = index - 1;
                        if(index < 0)
                        {
                            currentClk++;
                        }
                        continue;
                    }
                }
                else
                {
                    index = index - 1;
                    continue;
                }

            }
            else
            {
                index = numTasks - 1;
                continue;
            }
        }
        long fin = System.nanoTime(); // tiempo final
        // calculo de
        //duracion algoritmo en milisegundos
        duracionLIFO = round3((fin - inicio)/ 1_000_000.0);
        //  double totalLIFO;
        totalLIFO = round3(getAverageT());
        //    double esperaLIFO;
        esperaLIFO = round3(getAverageEspera());
        //    double servicioLIFO;
        servicioLIFO = round3(getAverageServicio());
        System.out.println("Finished processing Tasks through LIFO...");
        System.out.println("Results for each task in LIFO: ");
        printProcessedTasks();
    }

    // check and correct algorithm
    public void processTasksRoundRobin()
    {
        System.out.println("Processing Tasks through Round Robin...");
        long inicio = System.nanoTime();
        while(completedTasks < numTasks)
        {
            if(index<=numTasks-1)
            {
                if(!givenTasks.get(index).done)
                {
                    if(givenTasks.get(index).ti <= currentClk)
                    {
                        if(givenTasks.get(index).tcopy <= quantum)
                        {
                            givenTasks.get(index).tf = currentClk + givenTasks.get(index).tcopy;
                            givenTasks.get(index).T = givenTasks.get(index).tf - givenTasks.get(index).ti;
                            givenTasks.get(index).Espera = givenTasks.get(index).T - givenTasks.get(index).t;
                            givenTasks.get(index).Servicio = round3((double) givenTasks.get(index).t / givenTasks.get(index).T);
                            givenTasks.get(index).done = true;
                            currentClk =  givenTasks.get(index).tf;
                            completedTasks++;
                            index++;
                            qRound = true;
                        }
                        else
                        {
                            givenTasks.get(index).tcopy = givenTasks.get(index).tcopy-quantum;
                            currentClk =  currentClk + quantum;
                            index = index + 1;
                            qRound = true;
                            continue;
                        }
                    }
                    else
                    {
                        index = index + 1;
                        if(index == numTasks && !qRound)
                        {
                            currentClk++;
                        }
                        continue;
                    }
                }
                else
                {
                    index = index + 1;
                }
            }
            else
            {
                index = 0;
                qRound = false;
                continue;
            }
        }
        long fin = System.nanoTime(); // tiempo final
        // calculo de
        //duracion algoritmo en milisegundos
        duracionRR = round3((fin - inicio)/ 1_000_000.0);
        //  double totalLIFO;
        //  double totalRR;
        totalRR = round3(getAverageT());
        //    double esperaRR;
        esperaRR = round3(getAverageEspera());
        //    double servicioRR;
        servicioRR = round3(getAverageServicio());
        System.out.println("Finished processing Tasks through Round Robin...");
        System.out.println("Results for each task in Round Robin: ");
        printProcessedTasks();
    }

    public void compareServices()
    {
        double bestService;
        String nameBestService;
        if (servicioFIFO >= servicioLIFO && servicioFIFO >= servicioRR) {
            bestService = servicioFIFO;
            nameBestService = "FIFO";
        } else if (servicioLIFO >= servicioFIFO && servicioLIFO >= servicioRR) {
            bestService = servicioLIFO;
            nameBestService = "LIFO";
        } else {
            bestService = servicioRR;
            nameBestService = "RR";
        }
        System.out.println("The most optimal algorithm is " + nameBestService + " with a service of " + bestService);
    }

    public double round3(double value) {
        return Math.round(value * 1000.0) / 1000.0;
    }

}
