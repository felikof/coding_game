public class Exo {

	public int[][] rotate(int[][] array) {
   		int rows = array.length;
   		int cols = array[0].length;

   		int [][] rotatedArray = new int [cols][rows];
   		for (int i = rows - 1; i >= 0 ; i--) {
   			for (int j = 0; j < cols ; j++) {
   				rotatedArray[j][rows - i - 1] = array[i][j];
   			}
   		}
   		for (int i = 0; i < rotatedArray.length; ++i) {
	    	for (int j = 0; j < rotatedArray[i].length; ++j) {
	    		System.out.print(rotatedArray[i][j] + " ");
	    	}
	    	System.out.println("\n");
	    }
   		return rotatedArray;
	}

	public static int[][] rotateCCW(int[][] array) {
		int rows = array.length;
   		int cols = array[0].length;

   		int [][] rotatedCcw = new int [cols][rows];
   		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedCcw[cols - 1 - j][i] = array[i][j];
            }
        }
   		for (int i = 0; i < rotatedCcw.length; ++i) {
	    	for (int j = 0; j < rotatedCcw[i].length; ++j) {
	    		System.out.print(rotatedCcw[i][j] + " ");
	    	}
	    	System.out.println("\n");
	    }
	    return rotatedCcw;
	}

	public static void main(String []args) {
		int[][] image = {
	    {12, 47, 85, 23, 91, 34},
	    {56, 73, 10, 66, 29, 84},
	    {45, 20, 68, 74, 15, 93},
	    {38, 90, 55, 27, 49, 62},
	    {77, 31, 42, 99, 18, 53}};

	    int[][] image1 = {{12, 23, 30}, {16, 29, 54}};

	    Exo test = new Exo();
		test.rotateCCW(image);
		
		
	}

	/*
	/**
	 * Effectue une rotation de 90 degrés dans le sens horaire sur un tableau 2D de taille 5x6.
	 *
	 * @param array le tableau 2D d'entiers à faire pivoter
	 * @return le tableau 2D après la rotation de 90 degrés
	 
	public int[][] rotate(int[][] array) {
	   
	    return rotatedArray;
	}
		
	int rows = array.length;
        int cols = array[0].length;
        int[][] rotatedArray = new int[cols][rows]; // Initialisation du tableau 6x5 avec des zéros
        public static int[][] rotateCCW(int[][] array) { // Rotate Counter Clockwise
        */
}