import java.util.ArrayList;

import utils.FileTXT;

class App {

  private static FileTXT txt = new FileTXT("map");

  public static void main(String[] args) {
    transformIntoMatrix();
  }

  public static void transformIntoMatrix() {
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
  }
}