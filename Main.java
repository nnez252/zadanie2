import java.util.*;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // читаем текст из консоли
        System.out.println("Введите текст:");
        String text = scanner.nextLine();

        // получаем статистику
        Map<String, Integer> wordCountMap = getWordCountMap(text);
        List<Map.Entry<String, Integer>> topWords = getTopWords(wordCountMap);

        // выводим результат
        System.out.println("Количество слов в тексте: " + wordCountMap.size());
        System.out.println("TOP10:");
        for (int i = 0; i < Math.min(10, topWords.size()); i++) {
            Map.Entry<String, Integer> entry = topWords.get(i);
            System.out.println(entry.getValue() + " — " + entry.getKey());
        }
    }

    private static Map<String, Integer> getWordCountMap(String text) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        String[] words = text.split("\\s+");
        for (String word : words) {
            String normalizedWord = word.toLowerCase().replaceAll("[^\\p{L}]", "");
            if (!normalizedWord.isEmpty()) {
                int count = wordCountMap.getOrDefault(normalizedWord, 0);
                wordCountMap.put(normalizedWord, count + 1);
            }
        }
        return wordCountMap;
    }

    private static List<Map.Entry<String, Integer>> getTopWords(Map<String, Integer> wordCountMap) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordCountMap.entrySet());
        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()));
        return entries;
    }

}
