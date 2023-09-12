public class SaltoCaballo {
    private static final int fila = 4 ;                        // Número de filas en el tablero
    private static final int columna = 4;                       // Número de columnas en el tablero
    private static int[][] tablero = new int[fila][columna];         // El tablero donde realizamos el seguimiento de los movimientos
    private static int[] movimientosX = {2, 1, -1, -2, -2, -1, 1, 2}; // Movimientos horizontales del caballo
    private static int[] movimientosY = {1, 2, 2, 1, -1, -2, -2, -1}; // Movimientos verticales del caballo

    public static void main(String[] args) {
        iniciarTablero(); // Inicializa el tablero

        // Comienza desde la esquina superior izquierda (0,0) y realiza un seguimiento del primer movimiento
        if (movimientoCaballo(0, 0, 1)) {
            printSolution(); // Si encuentra una secuencia de movimientos, imprime el tablero
        } else {
            System.out.println("No se encontró solución."); // Si no se encuentra
        }
    }

    //Método que inicializa el tablero, con todas sus casillas no visitadas.
    private static void iniciarTablero() {
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                tablero[i][j] = -1;  // Cada casilla se asigna (-1)
            }
        }
    }


    //Método para validar que el caballo no realice un movimiento fuera de los límites del tablero o que no haya sido visitada.
    private static boolean validarMovimiento(int x, int y) {
        return (x >= 0 && x < fila && y >= 0 && y < columna && tablero[x][y] == -1); // la casilla (x, y) está dentro del tablero y no ha sido visitada
    }

    //Método para; que recibe como parámetros la posición inicial: fila (x), columna(y) y un movimiento (move) del caballo.
    private static boolean movimientoCaballo(int x, int y, int move) {
        if (move == fila * columna) {
            return true; // Valida si todas las casillas han sido visitadas
            //fila por columna significa todas las casillas, entonces hemos terminado y encontramos una solución.
        }

        //Se validan todas las direcciones posibles para el próximo movimiento del caballo (arriba, abajo, izquierda, derecha y las diagonales).
        for (int i = 0; i < 8; i++) {
            int nextX = x + movimientosX[i];
            int nextY = y + movimientosY[i];

            if (validarMovimiento(nextX, nextY)) {
                tablero[nextX][nextY] = move; // Registra el movimiento en el tablero
                if (movimientoCaballo(nextX, nextY, move + 1)) { //El código se llama a sí mismo, para ver si puede encontrar más movimientos desde la nueva casilla.
                    return true; // Si se encuentra una solución, se detiene y la devuelve
                } else {
                    tablero[nextX][nextY] = -1; // Retrocede si no encuentra una solución
                }
            }
        }

        return false; // No se encontró una solución en esta rama
    }

    //Este método imprime la solución, en el tablero muestra la secuencia de movimientos del caballo.
    private static void printSolution() {
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                System.out.print(tablero[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
