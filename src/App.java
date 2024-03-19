import java.util.ArrayList;

import utils.FileTXT;

class App {

  private static FileTXT txt = new FileTXT("map");

  public static void main(String[] args) {
    String[][] matrix = transformIntoMatrix();

    String value = "";
    int row = 0;
    int col = 0;

    while (value != "*") {

      if (col == matrix[0].length) {
        if (row == matrix.length-1) break;
        row++;
        col = 0;
      }

      value = matrix[row][col];
      if (isNumeric(value)) {
        System.out.print(matrix[row][col] + " -> ");
      }

      col++;
    }


  }

  private static String[][] transformIntoMatrix() {
    ArrayList<String> lines = txt.read();
    
    int columns = lines.get(0).length();
    int rows = lines.size();

    String[][] matrix = new String[rows][columns];

    for (int row = 0; row < rows; row++) {
      String[] chars = lines.get(row).split("");
      for (int col = 0; col < chars.length; col++) {
        matrix[row][col] = chars[col];
      }
    }

    return matrix;
  }

  private static boolean isNumeric(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}