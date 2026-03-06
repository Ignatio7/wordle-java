void main() {

    try (PrintWriter log = new PrintWriter("wordle.log")) {

        WordleDictionaryLoader loader = new WordleDictionaryLoader(log);
        WordleDictionary dictionary = loader.load("words.txt");

        WordleGame game = new WordleGame(dictionary, log);

        Scanner scanner = new Scanner(System.in);

        while (!game.isFinished()) {

            IO.println("Введите слово: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                IO.println("Подсказка: " + game.suggestWord());
                continue;
            }

            try {
                String result = game.makeTurn(input);
                IO.println(result);
            } catch (WordNotFoundInDictionary e) {
                IO.println("Слово отсутствует в словаре");
            } catch (InvalidWordException e) {
                IO.println("Слово должно состоять из 5 букв");
            }
        }

        IO.println("Загаданное слово: " + game.getAnswer());

    } catch (Exception e) {
        e.printStackTrace();
    }
}