package tms;

import java.io.*;
import java.util.*;

//handles basic file operations , help in saving and loading data
public class FileManager {

    // Write one line in file
    public void writeLine(String file, String line) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(file, true));
        out.println(line);
        out.close();
    }

    // Read all lines from file
    public List<String> readLines(String file) throws IOException {
        List<String> list = new ArrayList<>();
        File f = new File(file);
        if (!f.exists()) {
            return list;
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        sc.close();
        return list;
    }

    // Replace with new lines
    public void overwriteFile(String file, List<String> lines) throws IOException {
        PrintWriter out = new PrintWriter(file);
        for (String l : lines) {
            out.println(l);
        }
        out.close();
    }
}
