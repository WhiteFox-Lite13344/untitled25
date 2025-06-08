package com_company;



public enum KEYS implements Key {
    ID {
        @Override
        public String naming() { return "ID"; }
        @Override
        public String extract(Person person) { return String.valueOf(person.id()); }
    },
    SECTOR {
        @Override
        public String naming() { return "Сектор"; }
        @Override
        public String extract(Person person) { return person.sector(); }
    },
    SECTOR_NAME {
        @Override
        public String naming() { return "Сектор (имя)"; }
        @Override
        public String extract(Person person) { return person.sectorName(); }
    },
    ROW {
        @Override
        public String naming() { return "Ряд"; }
        @Override
        public String extract(Person person) { return person.row(); }
    },
    ROW_NAME {
        @Override
        public String naming() { return "Ряд (имя)"; }
        @Override
        public String extract(Person person) { return person.rowName(); }
    },
    SEAT {
        @Override
        public String naming() { return "Место"; }
        @Override
        public String extract(Person person) { return person.seat(); }
    },
    SEAT_NAME {
        @Override
        public String naming() { return "Место (имя)"; }
        @Override
        public String extract(Person person) { return person.seatName(); }
    },
    FULL_SECTOR {
        @Override
        public String naming() { return "Полный сектор"; }
        @Override
        public String extract(Person person) { return person.sectorName(); }
    },
    FULL_ROW {
        @Override
        public String naming() { return "Полный ряд"; }
        @Override
        public String extract(Person person) { return person.row() + " " + person.rowName(); }
    },
    FULL_SEAT {
        @Override
        public String naming() { return "Полное место"; }
        @Override
        public String extract(Person person) { return person.seat() + " " + person.seatName(); }
    }
}
