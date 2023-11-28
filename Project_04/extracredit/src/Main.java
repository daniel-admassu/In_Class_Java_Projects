import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * initiate a treeMap the holds a String key and Book value
     * reads the provide file, make a book form the information on each line and store in the treeMap
     * using the books ISBN
     * demonstrates some capabilities of the treeMap
     * @param args Command-line parameters; unused
     *
     */
    public static void main(String[] args)  {
        TreeMap<String,Book> myWordTree = new TreeMap<>();
        File file = new File("BooksDataFile.txt");
        Scanner input;
        try {
             input = new Scanner(file);
        }catch (FileNotFoundException e){
            return;
        }

        String line1 = input.nextLine();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] parts = line.split("~",10);

            Book book = new Book(parts[2],parts[3],Integer.parseInt(parts[4]),parts[5],
                    parts[6],Double.parseDouble(parts[7]));

            myWordTree.put(parts[2],book);
        }

        System.out.println(myWordTree.size());
        System.out.println(myWordTree.get("0375700455"));
        String[] a = new String[2];
        System.out.println(myWordTree.toKeyArray(a)[0]);
        System.out.println(myWordTree.toKeyArray(a)[9299]);
        System.out.println(myWordTree.containsKey(myWordTree.toKeyArray(a)[800]));
    }

}
