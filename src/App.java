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
  private static FileTXT txt = new FileTXT("casoC1500");
  private static int row = 0;
  private static int col = 0;
  
  public static void main(String[] args) {
    txt.removeEmptyLines();
    String[][] matrix = transformIntoMatrix();
    System.out.println("Carregando...");
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

    for (int r = 0; r < rows; r++) {
      String[] chars = lines.get(r).split("");
      
      for (int c = 0; c < chars.length; c++) {
        if(chars[c] != null){
          matrix[r][c] = chars[c];
          if (matrix[r][c].equals("-") && c == 0) {
            col = c;
            row = r;
          }
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