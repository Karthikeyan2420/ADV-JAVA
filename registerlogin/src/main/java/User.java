public class User {
    private String sname;
    private int age;
    private String photo;

    public User(String sname, int age, String photo) {
        this.sname = sname;
        this.age = age;
        this.photo = photo;
    }

    public String getSname() {
        return sname;
    }

    public int getAge() {
        return age;
    }

    public String getPhoto() {
        return photo;
    }
}
