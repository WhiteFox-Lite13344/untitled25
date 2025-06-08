package com_company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultParse implements Result {
    private final Result resultDelegate;
    private final Map<String, String> parsedValueMap = new LinkedHashMap<>();
    private static final Pattern SEAT_PATTERN = Pattern.compile(
            "^(?<sector>\\S+) (?<sectorName>[^\\s]+) (?<row>Ряд) (?<rowName>\\S+) (?<seat>Место) (?<seatName>\\S+)$"
    );

        public ResultParse(Result resultDelegate) {
        this.resultDelegate = resultDelegate;
    }

    @Override
    public void setValue(String value, long id) {
        resultDelegate.setValue(value, id);
    }

    @Override
    public void print() {
        parsedValueMap.clear(); // Очищаем перед новым парсингом
        parse(resultDelegate.toString());
    }

    @Override
    public boolean isValid(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        Matcher matcher = SEAT_PATTERN.matcher(value);
        return matcher.matches();
    }

    @Override
    public String toString() {
        if (parsedValueMap.isEmpty()) {
            return "Некорректный формат строки\n";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : parsedValueMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public long getId() {
        return resultDelegate.getId();
    }

    private void parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return; // Пустая строка не парсится
        }
        Matcher matcher = SEAT_PATTERN.matcher(input);
        if (!matcher.find()) {
            return; // Некорректный формат, оставляем parsedValues пустым
        }
        Person person = createPerson(matcher);
        fillParsedValues(person);
    }

    private Person createPerson(Matcher matcher) {
        return new Person(resultDelegate.getId(),
                matcher.group("sector"),
                matcher.group("sectorName"),
                matcher.group("row"),
                matcher.group("rowName"),
                matcher.group("seat"),
                matcher.group("seatName"));
    }

    private void fillParsedValues(Person person) {
        Map<String, String> entries = person.toMap();
        person.keys().forEach(key -> parsedValueMap.put(key.naming(), entries.get(key.naming())));
    }
}
