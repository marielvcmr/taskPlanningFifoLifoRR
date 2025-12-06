 # 📱Simulador de Planificación de Tareas en Java 🍵

<p>Este proyecto implementa un simulador de planificación de tareas utilizando tres algoritmos clásicos de sistemas operativos:</p>

* FIFO (First In, First Out)
* LIFO (Last In, First Out)
* Round Robin (RR)

El programa permite ingresar un conjunto de tareas con:
- Nombre
- Tiempo de llegada (ti)
- Duración o ráfaga de CPU (t)

Cada algoritmo procesa las tareas y calcula:
- Tiempo de finalización (tf)
- Tiempo de retorno (T)
- Tiempo de espera
- Índice de servicio
- Tiempo real de ejecución del algoritmo
- Promedios de T, Espera y Servicio
---
## ⚙️Funcionamiento del Programa

1. El usuario introduce:
   - Cantidad de tareas
   - Nombre de cada tarea
   - Tiempo de llegada (ti)
   - Duración (t)
   - Quantum (solo para Round Robin)

2. El sistema procesa las tareas usando:
   - **FIFO**
   - **LIFO**
   - **Round Robin**
    
3. Por cada algoritmo se calculan los tiempos finales, T, espera y tiempo de ejecución, además, se calcula servicio y los promedios de T, E, y Servicio 
4. El programa imprime:
   - Resultados por tarea
   -  Promedios T, E, Servicio promedio del algoritmo
   -  Tiempo real de ejecución del algoritmo dentro del programa
   - Nombre del algoritmo más óptimo, es decir, quel cuyo servicio es mayor
---
## 📌Objetivo del Proyecto

<p>Este simulador permite comprender de forma práctica el funcionamiento de los algoritmos de planificación de CPU y analizar su rendimiento dado un conjunto de tareas específico, con sus tiempos iniciales y de duración.</p>
