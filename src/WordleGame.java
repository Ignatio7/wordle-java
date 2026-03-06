import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

public class WordleGame {

    private static final int MAX_ATTEMPTS = 6;

    private final WordleDictionary dictionary;
    private final PrintWriter log;

    private final String answer;
    private int attempts;

    public WordleGame(WordleDictionary dictionary, PrintWriter log) {

        this.dictionary = dictionary;
        this.log = log;

        this.attempts = MAX_ATTEMPTS;

        List<String> words = dictionary.getWords();
        Random random = new Random();

        this.answer = words.get(random.nextInt(words.size()));

        log.println("Новая игра началась");
        log.println("Количество попыток: " + MAX_ATTEMPTS);
        log.println("Загаданное слово: " + answer);
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

        log.println("Попытка игрока: " + guess);
        log.println("Осталось попыток: " + attempts);

        if (guess.equals(answer)) {
            attempts = 0;
            log.println("Игрок угадал слово");
            return "+++++";
        }

        return WordleDictionary.compareWords(answer, guess);
    }

    public String suggestWord() {
        return dictionary.randomWord();
    }
}