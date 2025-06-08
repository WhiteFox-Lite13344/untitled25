package com.company;


import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Result base = new Result.Base();
//        Result parse = new Result.Parse(base);
//
//        base.add("������ A12 ��� 5 ����� 16",2L);
//        parse.output();
//        System.out.println(parse);
//
//        base.add("������ B13 ��� 4 ����� 1", 3L);
//        parse.output();
//        System.out.println(parse);

        // ������� ������ �������

        List<Ticket> tickets = List.of(
                new Ticket("������ A12 ��� 5 ����� 16", 2L),
                new Ticket("������ B13 ��� 4 ����� 1", 3L),
                new Ticket("������ C14 ��� 3 ����� 2", 4L),
                new Ticket("������ D15 ��� 2 ����� 3", 5L)
        );

        // ������� ��������� � 4 ��������
        TicketProcessor processor = new TicketProcessor(4);

        // ������������ ������
        processor.process(tickets);

        // ������� ����������
        System.out.println(processor.output());
    }
}

