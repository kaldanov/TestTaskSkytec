package task.db;

import java.util.ArrayList;
import java.util.List;

public class Clan {
    private long id;     // id клана
    private String name; // имя клана
    private int gold;    // текущее количество золота в казне клана
    private List<User> users = new ArrayList<>(); // OneToMany. У одного клана есть несколько игроков

    public Clan() {

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Clan(long id, String name, int gold, List<User> users) {
        this.id = id;
        this.name = name;
        this.gold = gold;
        this.users = users;
    }



    public Clan(long id, String name, int gold) {
        this.id = id;
        this.name = name;
        this.gold = gold;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
