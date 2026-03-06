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

                IO.println("Слова нет в словаре");
                log.println("Игрок ввёл слово вне словаря: " + input);

            } catch (InvalidWordException e) {

                IO.println("Слово должно состоять из 5 букв");
                log.println("Некорректная длина слова: " + input);
            }
        }

        IO.println("Игра окончена. Загаданное слово: " + game.getAnswer());

    } catch (Exception e) {

        try (PrintWriter log = new PrintWriter("wordle.log")) {
            log.println("Критическая ошибка программы:");
            e.printStackTrace(log);
        } catch (Exception ignored) {
        }
    }
}