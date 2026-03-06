import java.util.List;
import java.util.Random;

public record WordleDictionary(List<String> words) {

    public boolean contains(String word) {
        return words.contains(word);
    }

    public String randomWord() {
        return words.get(new Random().nextInt(words.size()));
    }

    public String normalize(String word) {
        return word.toLowerCase().replace('ё', 'е');
    }

    public static String compareWords(String answer, String guess) {

        StringBuilder result = new StringBuilder("-----");

        boolean[] used = new boolean[answer.length()];

        // сначала ищем точные совпадения
        for (int i = 0; i < guess.length(); i++) {

            if (guess.charAt(i) == answer.charAt(i)) {

                result.setCharAt(i, '+');
                used[i] = true;
            }
        }

        // затем ищем буквы на других позициях
        for (int i = 0; i < guess.length(); i++) {

            if (result.charAt(i) == '+') {
                continue;
            }

            char g = guess.charAt(i);

            for (int j = 0; j < answer.length(); j++) {

                if (!used[j] && answer.charAt(j) == g) {

                    result.setCharAt(i, '^');
                    used[j] = true;
                    break;
                }
            }
        }

        return result.toString();
    }
}