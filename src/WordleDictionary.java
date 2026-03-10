import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WordleDictionary {

    private final List<String> words;
    private final Set<String> wordSet;
    private final Random random = new Random();

    public WordleDictionary(List<String> words) {
        this.words = words;
        this.wordSet = new HashSet<>(words);
    }

    public List<String> getWords() {
        return words;
    }

    public boolean contains(String word) {
        return wordSet.contains(word);
    }

    public String randomWord() {
        return words.get(random.nextInt(words.size()));
    }

    public static String compareWords(String answer, String guess) {

        StringBuilder comparisonResult = new StringBuilder("-----");

        boolean[] usedAnswerLetters = new boolean[answer.length()];

        // ищем буквы на правильных позициях
        for (int guessIndex = 0; guessIndex < guess.length(); guessIndex++) {

            if (guess.charAt(guessIndex) == answer.charAt(guessIndex)) {

                comparisonResult.setCharAt(guessIndex, '+');
                usedAnswerLetters[guessIndex] = true;
            }
        }

        // ищем буквы на других позициях
        for (int guessIndex = 0; guessIndex < guess.length(); guessIndex++) {

            if (comparisonResult.charAt(guessIndex) == '+') {
                continue;
            }

            char guessedLetter = guess.charAt(guessIndex);

            for (int answerIndex = 0; answerIndex < answer.length(); answerIndex++) {

                if (!usedAnswerLetters[answerIndex]
                        && answer.charAt(answerIndex) == guessedLetter) {

                    comparisonResult.setCharAt(guessIndex, '^');
                    usedAnswerLetters[answerIndex] = true;
                    break;
                }
            }
        }

        return comparisonResult.toString();
    }
}