package com_company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public record Person(
        long id,
        String sector,
        String sectorName,
        String row,
        String rowName,
        String seat,
        String seatName) {



public Stream<Key> keys(){
    return Stream.of(KEYS.values());
}

public Map<String, String> toMap(){
    Map<String, String> map = new LinkedHashMap<>();
    keys().forEach(key -> map.put(key.naming(), key.extract(this)));
    return map;
}
}

