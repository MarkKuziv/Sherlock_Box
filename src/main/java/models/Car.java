package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cars")
public class Car {

    @Id
    @Column(name = "Cars_Id")
    private int Id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Model")
    private String Model;
    @Column (name = "Engine_Capacity")
    private float EngineCapacity;
    @Column(name = "User_Id")
    private User UserID;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public float getEngineCapacity() {
        return EngineCapacity;
    }

    public void setEngineCapacity(float engineCapacity) {
        EngineCapacity = engineCapacity;
    }

    public User getUserID() {
        return UserID;
    }

    public void setUserID(User userID) {
        UserID = userID;
    }

    public Car(String name, String model, float engineCapacity, User userID) {
        Name = name;
        Model = model;
        EngineCapacity = engineCapacity;
        UserID = userID;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Model='" + Model + '\'' +
                ", EngineCapacity=" + EngineCapacity +
                ", UserID=" + UserID +
                '}';
    }
}
