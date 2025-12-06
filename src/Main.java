//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        taskPlanning taskPlanning = new taskPlanning();
        taskPlanning.askTasks();
        taskPlanning.processTasksFIFO();

        taskPlanning.setTasksAsDefault();
        taskPlanning.setVariablesForLIFO();
        taskPlanning.processTasksLIFO();

        taskPlanning.setTasksAsDefault();
        taskPlanning.setVariablesForRoundRobin();
        taskPlanning.processTasksRoundRobin();

        System.out.println("T, E I Average for FIFO: " + taskPlanning.totalFIFO +" " + taskPlanning.esperaFIFO + " " + taskPlanning.servicioFIFO);
        System.out.println("La duracion de FIFO fue de " + taskPlanning.duracionFIFO + " milisegundos\n");
        System.out.println("T, E I Average for LIFO: " + taskPlanning.totalLIFO +" " + taskPlanning.esperaLIFO + " " + taskPlanning.servicioLIFO);
        System.out.println("La duracion de LIFO fue de " + taskPlanning.duracionLIFO + " milisegundos\n");
        System.out.println("T, E I Average for Round Robin: " + taskPlanning.totalRR +" " + taskPlanning.esperaRR + " " + taskPlanning.servicioRR);
        System.out.println("La duracion de Round Robin fue de " + taskPlanning.duracionFIFO + " milisegundos\n");
        taskPlanning.compareServices();
    }
}