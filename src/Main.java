import javax.swing.JOptionPane;
import java.util.ArrayList;

//Clase principal: contiene el menú y el punto de entrada del programa
public class Main {

    //menu: muestra el menú principal y maneja la interacción con el usuario
    public static void menu() {
        MinHeap minHeap = new MinHeap();
        boolean salir = false;

        while (!salir)
        {

            //onstruimos el texto del menú con el estado actual del heap
            String opciones =
                            " MONTÍCULO MÍNIMO (MIN-HEAP)     \n" +
                            "Heap actual: " + minHeap.mostrarHeap() + "\n" +
                            "Elementos: " + minHeap.getTamanio() + "\n\n" +
                            "Seleccione una opción:\n\n" +
                            "  1. Insertar elemento\n" +
                            "  2. Eliminar mínimo\n" +
                            "  3. Ver mínimo (peek)\n" +
                            "  4. Heapify (convertir arreglo a heap)\n" +
                            "  5. Salir";

            String seleccion = JOptionPane.showInputDialog(
                    null,
                    opciones,
                    "Min-Heap - SOFT-10",
                    JOptionPane.PLAIN_MESSAGE
            );

            //Si el usuario cierra el diálogo, salimos
            if (seleccion == null) {
                salir = true;
                continue;
            }

            switch (seleccion.trim()) {

                case "1": //insertar
                    String valorStr = JOptionPane.showInputDialog(
                            null,
                            "Ingrese el número a insertar:",
                            "Insertar",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    if (valorStr != null && !valorStr.trim().isEmpty()) {
                        try {
                            int valor = Integer.parseInt(valorStr.trim());
                            minHeap.insertar(valor);
                            JOptionPane.showMessageDialog(
                                    null,
                                    " Valor " + valor + " insertado correctamente.\n\n" +
                                            "Heap resultante: " + minHeap.mostrarHeap(),
                                    "Insertar",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Por favor ingrese un número entero válido.",
                                    "Error",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        }
                    }
                    break;

                case "2": //Eliminar mínimo
                    try {
                        int min = minHeap.eliminarMin();
                        JOptionPane.showMessageDialog(
                                null,
                                "Mínimo eliminado: " + min + "\n\n" +
                                        "Heap resultante: " + minHeap.mostrarHeap(),
                                "Eliminar Mínimo",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (RuntimeException e) {
                        JOptionPane.showMessageDialog(
                                null,
                                "⚠ " + e.getMessage(),
                                "Error",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                    break;

                case "3": //Peek
                    try {
                        int min = minHeap.peek();
                        JOptionPane.showMessageDialog(
                                null,
                                "El elemento mínimo actual es: " + min + "\n" +
                                        "(No fue eliminado del heap)",
                                "Ver Mínimo (peek)",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (RuntimeException e) {
                        JOptionPane.showMessageDialog(
                                null,
                                "error " + e.getMessage(),
                                "Error",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                    break;

                case "4": //Heapify
                    String arregloStr = JOptionPane.showInputDialog(
                            null,
                            "Ingrese números separados por comas.\n" +
                                    "Ejemplo: 9, 4, 7, 1, 3\n\n" +
                                    "NOTA: Esto reemplazará el heap actual.",
                            "Heapify",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    if (arregloStr != null && !arregloStr.trim().isEmpty()) {
                        try {
                            String[] partes = arregloStr.split(",");
                            ArrayList<Integer> arreglo = new ArrayList<>();
                            for (String parte : partes) {
                                arreglo.add(Integer.parseInt(parte.trim()));
                            }
                            //Mostramos el arreglo original antes del heapify
                            String original = arreglo.toString();
                            minHeap.heapify(arreglo);
                            JOptionPane.showMessageDialog(
                                    null,
                                    " Heapify completado.\n\n" +
                                            "Arreglo original: " + original + "\n" +
                                            "Heap resultante:  " + minHeap.mostrarHeap(),
                                    "Heapify",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    " Formato inválido. Use números enteros separados por comas\n" +
                                            "Ejemplo: 5, 2, 8, 1",
                                    "Error",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        }
                    }
                    break;

                case "5": //Salir
                    salir = true;
                    JOptionPane.showMessageDialog(
                            null,
                            "Programa finalizado.Hasta luego",
                            "Salir",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    break;

                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Opción inválida. Por favor elija un número del 1 al 5",
                            "Opción Inválida",
                            JOptionPane.WARNING_MESSAGE
                    );
            }
        }
    }

    //Punto de entrada del programa
    public static void main(String[] args) {
        menu();
    }
}