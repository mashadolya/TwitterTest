package framework;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import framework.utils.PropertyReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVUtils {
    private static final String SEPARATOR = PropertyReader.getPropertyOrDefault("csvSeparator", ",");
    private static final int INDEX_PARAMETER_NAME = 0;
    private static final int INDEX_PARAMETER_VALUE = 1;
    private static Log log = Log.getInstance();

    private static List<String[]> readData(final String FILE_PATH, final String FILE_NAME) {
        List<String[]> csvData = new ArrayList<>();
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(SEPARATOR.charAt(0))
                .build();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(String.format("%s%s", FILE_PATH, FILE_NAME)), StandardCharsets.UTF_8);
            CSVReader csvReader = new CSVReaderBuilder(inputStreamReader).withCSVParser(csvParser).build();
            csvData = csvReader.readAll();
        } catch (IOException e) {
            log.error("loc.err.read.csv");
        }
        return csvData;
    }

    public static Properties getProperties(final String FILE_PATH, final String FILE_NAME) {
        List<String[]> csvData = readData(FILE_PATH, FILE_NAME);
        Properties properties = new Properties();
        for (String[] record : csvData) {
            if (record.length > INDEX_PARAMETER_VALUE) {
                properties.put(record[INDEX_PARAMETER_NAME], record[INDEX_PARAMETER_VALUE]);
            }
        }
        return properties;
    }
}
