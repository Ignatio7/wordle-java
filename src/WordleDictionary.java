import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WordleDictionary {

    private final List<String> words;
    private final Set<String> wordSet;

    public WordleDictionary(List<String> words) {

        this.words = words;
        this.wordSet = new HashSet<>(words); // быстрый поиск
    }

    public List<String> getWords() {
        return words;
    }

    public boolean contains(String word) {
        return wordSet.contains(word);
    }

    public String randomWord() {
        return words.get(new Random().nextInt(words.size()));
    }

    public String normalize(String word) {
        return word.toLowerCase().replace('ё', 'е');
    }

    public static String compareWords(String answer, String guess) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < guess.length(); i++) {

            char g = guess.charAt(i);

            if (g == answer.charAt(i)) {
                result.append("+");
            } else if (answer.indexOf(g) >= 0) {
                result.append("^");
            } else {
                result.append("-");
            }
        }

        return result.toString();
    }
}