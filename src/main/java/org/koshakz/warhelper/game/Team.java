package org.koshakz.warhelper.game;

public enum Team {
    SPECTATOR("spectator"),
    RED("red"),
    GREEN("green");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
