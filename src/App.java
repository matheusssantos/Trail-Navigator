import java.util.ArrayList;

import utils.FileTXT;

enum Directions {
  LEFT,
  RIGHT,
  UP,
  DOWN,
  STOPED
}

class App {
  private static FileTXT txt = new FileTXT("casoC750");
  private static int row = 0;
  private static int col = 0;

  public static void main(String[] args) {
    String[][] matrix = transformIntoMatrix();
    
    String numbers = "";
    String value = "";
    Directions direction = Directions.RIGHT;

    while (value != "#") {
      if (direction == Directions.RIGHT) col++;
      if (direction == Directions.LEFT) col--;
      if (direction == Directions.DOWN) row++;
      if (direction == Directions.UP) row--;
      
      if (row < matrix.length && col < matrix[0].length) {
        value = matrix[row][col];
        
        if (isNumeric(value)) {
          numbers += value;
        } else {
          numbers += " ";
        }
      } else {
        System.out.println(" ERRO ");
        break;
      }

      if (value.equals("\\")) {
        if (direction == Directions.DOWN)
          direction = Directions.RIGHT;
        else if (direction == Directions.UP)
          direction = Directions.LEFT;
        else if (direction == Directions.LEFT)
          direction = Directions.UP;
        else if (direction == Directions.RIGHT)
          direction = Directions.DOWN;

      } 
      else if (value.equals("/")) {
        if (direction == Directions.DOWN)
          direction = Directions.LEFT;
        else if (direction == Directions.RIGHT)
          direction = Directions.UP;
        else if (direction == Directions.UP)
          direction = Directions.RIGHT;
        else
          direction = Directions.DOWN;

      }
      // else if (value.equals("*")) {
      //   direction = Directions.RIGHT;

      // }
      else if (value.equals("#")) {
        break;
      }

    }

    String[] numbersList = numbers.split(" ");
    int total = 0;
    for (String n : numbersList) {
      if (!n.equals("")) {
        total += Integer.parseInt(n);
      }
    };
    
    System.out.println("Total: " + total);
  }

  private static String[][] transformIntoMatrix() {
    ArrayList<String> lines = txt.read();
    
    int columns = lines.get(0).length();
    int rows = lines.size();

    String[][] matrix = new String[rows][columns];

    for (int r = 0; r < rows-1; r++) {
      String[] chars = lines.get(r).split("");
      
      for (int c = 0; c < chars.length-1; c++) {
        matrix[r][c] = chars[c];
        
        if (matrix[r][c].equals("-") && c == 0) {
          // matrix[r][c] = "*"; 
          System.out.println("salvou");
          col = c;
          row = r;
        }
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