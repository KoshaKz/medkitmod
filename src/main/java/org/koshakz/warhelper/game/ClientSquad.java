package org.koshakz.warhelper.game;

import java.util.ArrayList;

public class ClientSquad {


    public String name;
    public String owner;
    public String[] members;
    public int maxMembers;
    public boolean isOpen;

    public ClientSquad(String name, String owner, String[] members, int maxMembers) {
        this.name = name;

        this.owner = owner;
        this.members = members;
        this.maxMembers = maxMembers;
    }


    public void switchOpenOption() {
        isOpen = !isOpen;
    }


}
