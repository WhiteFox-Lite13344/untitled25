package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Result  {
    void add(String value,long id);
    void output();
    long id();
    boolean isValid(String value);


    class Base implements Result {
        private String valueResult;
        private long id;
        public Base() {
            this("",0L);
        }

        public Base(String value,long id) {
            this.valueResult = value;
            this.id = id;
        }

        @Override
        public void add(String value,long id) {
            this.valueResult = safe(value);
            this.id = id;
        }

        @Override
        public void output() {
         if(!isValid(valueResult)){
             valueResult = "";
         }
        }

        @Override
        public boolean isValid(String value) {
            return value != null && !value.trim().isEmpty();
        }

        @Override
        public long id() {
            return id;
        }

        private String safe(String value) {
            return value == null ? "" : value.trim();
        }

        @Override
        public String toString() {
            return valueResult;
        }
    }

    class Parse implements Result {
        private final Result output;
        private final Map<String, String> parsedValues = new LinkedHashMap<>();
        private static final Pattern SEAT_PATERN = Pattern.compile(
                "^(?<sector>\\S+) (?<sectorName>[^\\s]+) (?<row>Ряд) (?<rowName>\\S+) (?<seat>Место) (?<seatName>\\S+)$"
        );

        public Parse(Result output) {
            this.output = output;
        }

        @Override
        public void add(String value, long id) {
            output.add(value, id);
        }

        @Override
        public void output() {
            parsedValues.clear(); // Очищаем перед новым парсингом
            parse(output.toString());
        }

        @Override
        public boolean isValid(String value) {
            if (value == null || value.trim().isEmpty()) {
                return false;
            }
            Matcher matcher = SEAT_PATERN.matcher(value);
            return matcher.matches();
        }

        @Override
        public String toString() {
            if (parsedValues.isEmpty()) {
                return "Некорректный формат строки\n";
            }
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : parsedValues.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            return sb.toString();
        }

        @Override
        public long id() {
            return output.id();
        }

        private void parse(String input) {
            if (input == null || input.trim().isEmpty()) {
                return; // Пустая строка не парсится
            }
            Matcher matcher = SEAT_PATERN.matcher(input);
            if (!matcher.find()) {
                return; // Некорректный формат, оставляем parsedValues пустым
            }
            Person person = creatPerson(matcher);
            fillParsedValues(person);
        }

        private Person creatPerson(Matcher matcher) {
            return new Person(output.id(),
                    matcher.group("sector"),
                    matcher.group("sectorName"),
                    matcher.group("row"),
                    matcher.group("rowName"),
                    matcher.group("seat"),
                    matcher.group("seatName"));
        }

        private void fillParsedValues(Person person) {
            Map<String, String> entries = person.toMap();
            person.keys().forEach(key -> parsedValues.put(key.naming(), entries.get(key.naming())));
        }
    }
}