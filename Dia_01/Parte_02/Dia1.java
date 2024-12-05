package Parte_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dia1 {
    public static void main(String[] args) {

        ArrayList<Integer> coluna1 = new ArrayList<>();
        ArrayList<Integer> coluna2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Dia_01//input.txt"))) {
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

        int total = 0;
        for (Integer col1 : coluna1) {
            int quantidade = 0;
            for (Integer col2 : coluna2) {
                if (col1.equals(col2)) {
                    quantidade++;
                }
            }
            total += col1 * quantidade;
        }

        System.out.println("Total: " + total);
    }
}