import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Dia1 {
    public static void main(String[] args) {

        ArrayList<Integer> coluna1 = new ArrayList<>();
        ArrayList<Integer> coluna2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Dia 01/Parte 01/input.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.trim().split("\\s+");
                if (parts.length == 2) {
                    coluna1.add(Integer.parseInt(parts[0]));
                    coluna2.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(coluna1);
        Collections.sort(coluna2);
        
        int total = 0;
        for (int i = 0; i < coluna1.size(); i++) {
            total += Math.abs(coluna1.get(i) - coluna2.get(i));
        }

        System.out.println("Total: " + total);
    }
}