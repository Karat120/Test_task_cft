import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String DEFAULT_FILE_NAME_INTEGERS = "integers.txt";
    private static final String DEFAULT_FILE_NAME_FLOATS = "floats.txt";
    private static final String DEFAULT_FILE_NAME_STRINGS = "strings.txt";

    public static void main(String[] args) {

        String path = "";
        String prefix = "";
        boolean statType = true;
        boolean updateFile = true;
        List<String> files = new ArrayList<>();
        List<BigInteger> ints = new ArrayList<>();
        List<Float> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-o":
                        path = args[i + 1].charAt(0) == '/' ? args[i + 1].substring(1) : args[i + 1];
                        i++;
                        break;
                    case "-p":
                        prefix = args[i + 1];
                        i++;
                        break;
                    case "-s":
                        statType = false;
                        break;
                    case "-a":
                        updateFile = false;
                        break;
                    case "-f":
                        statType = true;
                        break;
                    default:
                        files.add(args[i]);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        for (String filePath : files) {
            List<String> content;
            try {
                content = Files.readAllLines(Paths.get(path.isEmpty()?filePath:path + File.separator + filePath));
            } catch (Exception e) {
                System.out.println("File not found");
                return;
            }
            for (String line : content) {
                switch (getType(line)) {
                    case "int":
                        ints.add(new BigInteger(line));
                        break;
                    case "float":
                        floats.add(Float.valueOf(line));
                        break;
                    default:
                        strings.add(line);
                        break;
                }
            }
        }
        statistic(ints, floats, strings, statType);
        if (updateFile) {
            saveOutputDataInt(ints, path + File.separator + prefix + DEFAULT_FILE_NAME_INTEGERS);
            saveOutputDataFloat(floats, path + File.separator + prefix + DEFAULT_FILE_NAME_FLOATS);
            saveOutputData(strings, path + File.separator + prefix + DEFAULT_FILE_NAME_STRINGS);
        } else {
            if (!ints.isEmpty()) {
                saveOutputDataInt(ints, path + File.separator + prefix + DEFAULT_FILE_NAME_INTEGERS);
            }
            if (!floats.isEmpty()) {
                saveOutputDataFloat(floats, path + File.separator + prefix + DEFAULT_FILE_NAME_FLOATS);
            }
            if (!strings.isEmpty()) {
                saveOutputData(strings, path + File.separator + prefix + DEFAULT_FILE_NAME_STRINGS);
            }
        }
    }

    private static void saveOutputData(List<String> data, String filePath) {
        try {
            // Создаем FileWriter, чтобы записать данные в файл
            FileWriter fileWriter = new FileWriter(filePath.charAt(0) == '\\' ? filePath.substring(1) : filePath);

            // Создаем BufferedWriter для более эффективной записи данных
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Записываем каждую строку массива в файл
            for (Object line : data) {
                bufferedWriter.write(String.valueOf(line));
                // Добавляем символ новой строки
                bufferedWriter.newLine();
            }

            // Закрываем BufferedWriter и FileWriter
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveOutputDataInt(List<BigInteger> data, String filePath) {
        try {
            // Создаем FileWriter, чтобы записать данные в файл
            FileWriter fileWriter = new FileWriter(filePath.charAt(0) == '\\' ? filePath.substring(1) : filePath);

            // Создаем BufferedWriter для более эффективной записи данных
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Записываем каждую строку массива в файл
            for (Object line : data) {
                bufferedWriter.write(String.valueOf(line));
                // Добавляем символ новой строки
                bufferedWriter.newLine();
            }

            // Закрываем BufferedWriter и FileWriter
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveOutputDataFloat(List<Float> data, String filePath) {
        try {
            // Создаем FileWriter, чтобы записать данные в файл
            FileWriter fileWriter = new FileWriter(filePath.charAt(0) == '\\' ? filePath.substring(1) : filePath);

            // Создаем BufferedWriter для более эффективной записи данных
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Записываем каждую строку массива в файл
            for (Object line : data) {
                bufferedWriter.write(String.valueOf(line));
                // Добавляем символ новой строки
                bufferedWriter.newLine();
            }

            // Закрываем BufferedWriter и FileWriter
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getType(String line) {
        if (line.matches("^[+-]?\\d+")) {
            return "int";
        }
        if (line.matches("[-+]?\\d*\\.\\d*E?[-+]?\\d*?")) {
            return "float";
        }
        return "str";
    }

    private static void statistic(List<BigInteger> intList, List<Float> floatList, List<String> stringList, Boolean type) {
        System.out.println("Statistic:");
        System.out.println("lines: " + intList.size() + floatList.size() + stringList.size());
        if (type) {
            BigInteger min = null;
            BigInteger max = null;
            BigInteger avg = null;
            BigInteger sum = BigInteger.ZERO;
            for (BigInteger value : intList) {
                if (min == null || min.compareTo(value) > 0) {
                    min = value;
                }
                if (max == null || max.compareTo(value) < 0) {
                    max = value;
                }
                sum = sum.add(value);
            }
            avg = sum.divide(BigInteger.valueOf(intList.size()));
            System.out.println("min " + min + " max " + max + " sum " + sum + " avg " + avg);

            Float minFloat = null;
            Float maxFloat = null;
            Float avgFloat = null;
            float sumFloat = 0F;
            for (Float valueFloat : floatList) {
                if (minFloat == null || minFloat.compareTo(valueFloat) > 0) {
                    minFloat = valueFloat;
                }
                if (maxFloat == null || maxFloat.compareTo(valueFloat) < 0) {
                    maxFloat = valueFloat;
                }
                sumFloat = sumFloat + (valueFloat);
            }
            avgFloat = sumFloat / ((float) floatList.size());
            System.out.println("min " + minFloat + " max " + maxFloat + " sum " + sumFloat + " avg " + avgFloat);

            int minString = 0;
            int maxString = 0;

            for (String line : stringList) {
                if (minString > line.length() || maxString == 0) {
                    minString = line.length();
                }
                if (maxString < line.length()) {
                    maxString = line.length();
                }
            }
            System.out.println("min " + minString + " max " + maxString);
        }
    }
}