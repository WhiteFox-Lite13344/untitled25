package com.company;


import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Result base = new Result.Base();
//        Result parse = new Result.Parse(base);
//
//        base.add("Сектор A12 Ряд 5 Место 16",2L);
//        parse.output();
//        System.out.println(parse);
//
//        base.add("Сектор B13 Ряд 4 Место 1", 3L);
//        parse.output();
//        System.out.println(parse);

        // Создаем список билетов

        List<Ticket> tickets = List.of(
                new Ticket("Сектор A12 Ряд 5 Место 16", 2L),
                new Ticket("Сектор B13 Ряд 4 Место 1", 3L),
                new Ticket("Сектор C14 Ряд 3 Место 2", 4L),
                new Ticket("Сектор D15 Ряд 2 Место 3", 5L)
        );

        // Создаем процессор с 4 потоками
        TicketProcessor processor = new TicketProcessor(4);

        // Обрабатываем билеты
        processor.process(tickets);

        // Выводим результаты
        System.out.println(processor.output());
    }
}

