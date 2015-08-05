package battleship;

public class TwoDArrays {
    
    public static void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
   
    public static void main(String[] args) {
        int[][] matrix1 = new int[3][3];
        int[][] matrix2 = new int[3][3];
        int[][] sumMatrix = new int[3][3];
        
        //randomly populate matrix1 and matrix2
        //ideally we should have made a method out of this
        //but this will serve the purposes of a demo of 2D arrays.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix1[i][j] = (int)(Math.random() * 10);
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix2[i][j] = (int)(Math.random() * 10);
            }
        }
        
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        
        System.out.println("Matrix1");
        print2DArray(matrix1); 
        System.out.println("Matrix2");
        print2DArray(matrix2); 
        System.out.println("Sum");
        print2DArray(sumMatrix); 
    }
}
