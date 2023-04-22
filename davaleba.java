import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NumberUtils {
  
  private NumberUtils() {
  }
  
  public static int[] readNumbersFromFile(String filename) throws IOException, NumberFormatException, InvalidNumberException {
    int[] numbers = new int[10];
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    try {
      for (int i = 0; i < 10; i++) {
        String line = reader.readLine();
        if (line == null) {
          throw new InvalidNumberException("Not enough numbers in file");
        }
        try {
          numbers[i] = Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
          throw new InvalidNumberException("Invalid number in file: " + line);
        }
      }
      return numbers;
    } finally {
      reader.close();
    }
  }
  
  public static int sum(int[] numbers) throws ArithmeticException {
    int sum = 0;
    for (int number : numbers) {
      sum = Math.addExact(sum, number);
    }
    return sum;
  }
  
  public static class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
      super(message);
    }
  }

  public static void main(String[] args) {
    String filename = "ragac.txt";
    
    try {
      int[] numbers = NumberUtils.readNumbersFromFile(filename);
      int sum = NumberUtils.sum(numbers);
      
      System.out.println("Numbers read from file:");
      for (int number : numbers) {
        System.out.println(number);
      }
      
      System.out.println("Sum of numbers: " + sum);
    } catch (NumberUtils.InvalidNumberException e) {
      System.err.println("Error reading numbers from file: " + e.getMessage());
    } catch (ArithmeticException e) {
      System.err.println("Error computing sum: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
  }
  }
  
