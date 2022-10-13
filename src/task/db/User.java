package task.db;

public class User {
    private long id;     // id user
    private String name; // name user

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

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
