public class Sudoku {

    private int sudoku[][];
    private int n = 9;

    public Sudoku(int sudoku[][]) {
        this.sudoku = sudoku;
    }

    public int getNumber(int l, int k) {
    		return sudoku[l][k];
    }
    
    public int setNumber(int r, int c, int n) {
    		return 0; 
    }
    
    public void solve() {
        if (!backtrackSolve()) {
            System.out.println("Denna sudoku kan inte lösas");
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
    /**Kollar om det är möjligt att sätta x i en viss specifik ruta
     */
    public boolean ifPossible(int i, int j, int x) {
        // Undersöker om x finns med på brädan horisontellt, dvs i de olika raderna. Om den finns returneras false
        for (int col = 0; col < n; col++) {
            if (sudoku[i][col] == x) {
                return false;
            }
        }
        // Undersöker om x finns med på brädan vertikalt, dvs i de olika kolumnerna 
        for (int r = 0; r < n; r++) {
            if (sudoku[r][j] == x) {
                return false;
            }
        }
        // Undersöker om x finns inom en viss region, 3x3 box
        int boxRow = i - i % 3;
        int boxColumn = j - j % 3;

        for (int ii = 0; ii < 3; ii++) {
            for (int jj = 0; jj < 3; jj++) {
                if (sudoku[boxRow + ii][boxColumn + jj] == x) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean backtrackSolve() {
        int i = 0;
        int j = 0;
        boolean ifEmptybox = false;

        for (int k = 0; k < n && !ifEmptybox; k++) {
            for (int l = 0; l < n && !ifEmptybox; l++) {
                if (sudoku[k][l] == 0) { //Om rutan är tom sätts ifEmptybox = true.
                    ifEmptybox = true;
                    i = k;
                    j = l;
                }
            }
        }
        if (!ifEmptybox) {
            return true;
        }

        for (int x = 1; x < 10; x++) {
        	
            if (ifPossible(i, j, x)) {
                sudoku[i][j] = x; //Efter att ha kollat så att x inte finns med på brädan så lägger vi till x på rad i på kolum j

                if (backtrackSolve()) { //rekursivt kollar om detta sedan leder till en lösning eller inte. 
                	return true;		//om det inte leder till en lösning, så provar vi nästa nummer för den aktuella tomma rutan 
                						
                }
                sudoku[i][j] = 0; // Om inga av numren från 1 till 9 leder till en lösning, returneras false
            }
        }
        return false; // Backtracking
    }
}


    
