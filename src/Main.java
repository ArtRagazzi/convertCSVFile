import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        System.out.println("Digite o diretorio: ");
        String path = sc.nextLine();
        File sourceFile = new File(path);
        String sourceFolderStr = sourceFile.getParent();

        boolean success = new File(sourceFolderStr + "/out").mkdir();
        List<String> newList = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();

            while(line != null){
               String[] fields = line.split(",");
               String name = fields[0];
               int quantidade = Integer.parseInt(fields[1]);
               Double price = Double.valueOf(fields[2]);
               newList.add(name+ " : "+ String.valueOf(quantidade*price));
               line = br.readLine();
            }
        }catch (IOException e){
           System.out.println("Error: " + e.getMessage());
        }

        String strPath = sourceFolderStr + "/out/summary.csv";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(strPath))){
            for(String line : newList){
                bw.write(line);
                bw.newLine();
            }
        }catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }


        sc.close();

    }
}