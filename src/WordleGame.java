import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

public class WordleGame {

    private final WordleDictionary dictionary;

    private final String answer;
    private int attempts = 6;

    public WordleGame(WordleDictionary dictionary, PrintWriter log) {

        this.dictionary = dictionary;

        List<String> words = dictionary.words();
        Random random = new Random();

        this.answer = words.get(random.nextInt(words.size()));

        log.println("Ответ игры: " + answer);
    }

    public boolean isFinished() {
        return attempts <= 0;
    }

    public String getAnswer() {
        return answer;
    }

    public String makeTurn(String guess)
            throws WordNotFoundInDictionary, InvalidWordException {

        guess = dictionary.normalize(guess);

        if (guess.length() != 5) {
            throw new InvalidWordException();
        }

        if (!dictionary.contains(guess)) {
            throw new WordNotFoundInDictionary();
        }

        attempts--;

        if (guess.equals(answer)) {
            attempts = 0;
            return "+++++";
        }

        return WordleDictionary.compareWords(answer, guess);
    }

    public String suggestWord() {
        return dictionary.randomWord();
    }
}