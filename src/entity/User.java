package entity;

public class User {
    private String Name;
    private String Nic;
    private String Mobile;
    private String BirthDay;
    private String Address;
    private String Position;
    private String Email;
    private String Password;

    public User() {
    }

    public User(String name, String nic, String mobile, String birthDay, String address, String position, String email, String password) {
        setName(name);
        setNic(nic);
        setMobile(mobile);
        setBirthDay(birthDay);
        setAddress(address);
        setPosition(position);
        setEmail(email);
        setPassword(password);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Nic='" + Nic + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", BirthDay='" + BirthDay + '\'' +
                ", Address='" + Address + '\'' +
                ", Position='" + Position + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
