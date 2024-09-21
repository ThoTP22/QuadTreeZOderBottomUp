import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> readDataFromFile(String fileName) throws Exception {
        List<String> result = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File " + fileName + " does not exist!");
            return result;
        }

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            result.add(line);
        }
        br.close();
        fr.close();
        System.out.println(">> Load file successfully!");
        return result;
    }
}
