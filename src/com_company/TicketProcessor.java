package com_company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class TicketProcessor {
    private final List<Result> results;
    private final ExecutorService executor;

    public TicketProcessor(int threadCount) {
        this.executor = Executors.newFixedThreadPool(threadCount);
        this.results = new ArrayList<>();
    }

    public void process(List<Ticket> tickets) {
        List<CompletableFuture<Void>> futures = tickets.stream()
                .map(this::processTicket)
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executor.shutdown();
    }

    private CompletableFuture<Void> processTicket(Ticket ticket) {
        return CompletableFuture.runAsync(() -> {
            ResultBase base = new ResultBase();
            ResultParse parse = new ResultParse(base);

            parse.setValue(ticket.value(), ticket.id());
            parse.print();

            synchronized (results) {
                results.add(parse);
            }
        }, executor);
    }

    public String print() {
        StringBuilder result = new StringBuilder();
        results.forEach(parse -> result.append(parse.toString()).append("\n"));
        return result.toString();
    }
}