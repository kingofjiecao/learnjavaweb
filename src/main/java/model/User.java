package model;

public class User {
    private final String username;
    private final String password;
    private final int id;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public User(Builder builder){
        this.username = builder.username;
        this.password = builder.password;
        this.id = builder.id;
    }

    @Override
    public String toString() {
        return "{username = " + username + ", password = " + password + ", id = " + id + "}";
    }

    public static class Builder{
        private String username;
        private String password;
        private int id;

        public Builder(String username, String password){
            this.username = username;
            this.password = password;
        }
        public Builder id(int id){
            this.id = id;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
}
