import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class WordleDictionaryLoader {

    public WordleDictionaryLoader(PrintWriter log) {
    }

    public WordleDictionary load(String filename) throws Exception {

        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.toLowerCase().replace('ё', 'е');

                if (line.length() == 5) {
                    words.add(line);
                }
            }

        }

        if (words.isEmpty()) {
            throw new RuntimeException("Словарь пуст");
        }

        return new WordleDictionary(words);
    }
}