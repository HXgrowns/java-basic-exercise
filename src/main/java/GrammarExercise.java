import java.util.*;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner scanner = new Scanner(System.in);
        String firstWordList = scanner.nextLine();
        String secondWordList = scanner.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);
        //按要求输出到命令行
        result.forEach(System.out::println);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        List<String> afterParseFirst = parseString(firstWordList);
        List<String> afterParseSecond = parseString(secondWordList);
        List<String> result = new ArrayList<>();

        afterParseFirst.forEach((word) -> {
            if (afterParseSecond.contains(word)) {
                StringBuilder temp = new StringBuilder(word.charAt(0) + "");
                word.chars().forEach((c) -> {
                    temp.append(" ").append((char) c);
                });
                result.add(temp.substring(2).toString());
            }
        });
        return result;
    }

    public static List<String> parseString(String string) {
        String[] strings = string.toUpperCase().split(",");

        HashSet<String> setStrings = new HashSet<>(Arrays.asList(strings));
        String[] dedupStrings = setStrings.toArray(new String[0]);
        Arrays.sort(dedupStrings);

        Arrays.stream(dedupStrings).forEach((s) -> {
            if (s.equals("")) {
                throw new RuntimeException("input not valid");
            }
            s.chars().forEach((c) -> {
                if (c < 'A' || c > 'Z') {
                    throw new RuntimeException("input not valid");
                }
            });
        });

        return Arrays.asList(dedupStrings);
    }
}