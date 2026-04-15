

import java.util.ArrayList;

//Clase que implementa un montículo mínimo min-heap usando un ArrayList
public class MinHeap {

    private ArrayList<Integer> heap;

    //Constructor inicializa el arreglo vacío
    public MinHeap()
    {
        heap = new ArrayList<>();
    }

    //retorna el índice del padre de un nodo en el índice i
    private int indicePadre(int i)
    {
        return (i - 1) / 2;
    }

    //Retorna el índice del hijo izquierdo de un nodo en el índice i
    private int indiceHijoIzq(int i)
    {
        return 2 * i + 1;
    }

    //Retorna el índice del hijo derecho de un nodo en el índice i
    private int indiceHijoDer(int i)
    {
        return 2 * i + 2;
    }

    //Intercambia dos elementos del arreglo dado sus índices
    private void intercambiar(int i, int j)
    {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


    //upHeapify sube el nodo en el índice i hasta su posición correcta
    private void upHeapify(int i)
    {
        //Mientras el nodo tenga padre y sea menor que él, sube
        while (i > 0) {
            int padre = indicePadre(i);
            if (heap.get(i) < heap.get(padre)) {
                intercambiar(i, padre);
                i = padre; //continua subiendo desde la nueva posición
            } else {
                break; //ya está en su lugar correcto
            }
        }
    }

    //downHeapify:baja el nodo en el índice i hasta su posición correcta
    private void downHeapify(int i) {
        int n = heap.size();

        //Mientras haya hijos que revisar
        while (true) {
            int menor = i;           // asumo que el nodo actual es el menor
            int hijoIzq = indiceHijoIzq(i);
            int hijoDer = indiceHijoDer(i);

            //Si el hijo izquierdo existe y es menor que el actual menor
            if (hijoIzq < n && heap.get(hijoIzq) < heap.get(menor))
            {
                menor = hijoIzq;
            }

            //Si el hijo derecho existe y es menor que el actual menor
            if (hijoDer < n && heap.get(hijoDer) < heap.get(menor))
            {
                menor = hijoDer;
            }

            //Si encontramos un hijo más pequeño, intercambiamos y continuamos
            if (menor != i)
            {
                intercambiar(i, menor);
                i = menor; //continua bajando desde la nueva posición
            } else {
                break; //ya está en su lugar correcto
            }
        }
    }


    //insertar agrega un valor al final y lo sube a su lugar correcto
    public void insertar(int valor)
    {
        heap.add(valor);
        upHeapify(heap.size() - 1); //sube el último elemento insertado
    }

    //eliminarMin: retorna y elimina la raíz el mínimo del heap
    public int eliminarMin()
    {
        if (heap.isEmpty())
        {
            throw new RuntimeException("El montículo está vacío.");
        }

        int min = heap.get(0); //guardo el mínimo para retornarlo

        //Movemos el último elemento a la raíz y lo eliminamos del final
        int ultimo = heap.remove(heap.size() - 1);

        //si todavía hay elementos, ponemos el último en la raíz y bajamos
        if (!heap.isEmpty())
        {
            heap.set(0, ultimo);
            downHeapify(0);
        }

        return min;
    }

    //peek: retorna el mínimo sin eliminarlo
    public int peek()
    {
        if (heap.isEmpty())
        {
            throw new RuntimeException("El montículo está vacío");
        }
        return heap.get(0);
    }

    //heapify:
    //convierte un arreglo desordenado en un min-heap válido
    public void heapify(ArrayList<Integer> arreglo)
    {
        heap = new ArrayList<>(arreglo); //copiamos el arreglo recibido

        //Empezamos desde el último nodo que tiene hijos y bajamos hasta la raíz
        //Esto convierte el arreglo en un heap válido de forma eficiente
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            downHeapify(i);
        }
    }
    //Retorna el heap como texto para mostrarlo
    public String mostrarHeap()
    {
        if (heap.isEmpty())
        {
            return "(vacío)";
        }
        return heap.toString();
    }

    //Retorna true si el heap está vacío
    public boolean estaVacio() {
        return heap.isEmpty();
    }

    //Retorna la cantidad de elementos en el heap
    public int getTamanio() {
        return heap.size();
    }
}