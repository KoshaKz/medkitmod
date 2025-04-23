package org.koshakz.warhelper.game;

import java.util.ArrayList;

public class Squad {
    public ArrayList<WarPlayer> squadPlayers = new ArrayList<>();
    public String name;
    public WarPlayer owner;

    public Squad(WarPlayer owner, String name) {
        squadPlayers.add(owner);
        this.owner = owner;
        this.name = name;
    }

    public ArrayList<WarPlayer> getPlayers() {
        return squadPlayers;
    }
}
