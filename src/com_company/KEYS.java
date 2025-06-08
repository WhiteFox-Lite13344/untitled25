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
        public String naming() { return "������"; }
        @Override
        public String extract(Person person) { return person.sector(); }
    },
    SECTOR_NAME {
        @Override
        public String naming() { return "������ (���)"; }
        @Override
        public String extract(Person person) { return person.sectorName(); }
    },
    ROW {
        @Override
        public String naming() { return "���"; }
        @Override
        public String extract(Person person) { return person.row(); }
    },
    ROW_NAME {
        @Override
        public String naming() { return "��� (���)"; }
        @Override
        public String extract(Person person) { return person.rowName(); }
    },
    SEAT {
        @Override
        public String naming() { return "�����"; }
        @Override
        public String extract(Person person) { return person.seat(); }
    },
    SEAT_NAME {
        @Override
        public String naming() { return "����� (���)"; }
        @Override
        public String extract(Person person) { return person.seatName(); }
    },
    FULL_SECTOR {
        @Override
        public String naming() { return "������ ������"; }
        @Override
        public String extract(Person person) { return person.sectorName(); }
    },
    FULL_ROW {
        @Override
        public String naming() { return "������ ���"; }
        @Override
        public String extract(Person person) { return person.row() + " " + person.rowName(); }
    },
    FULL_SEAT {
        @Override
        public String naming() { return "������ �����"; }
        @Override
        public String extract(Person person) { return person.seat() + " " + person.seatName(); }
    }
}
