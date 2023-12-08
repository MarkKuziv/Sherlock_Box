package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Order")
public class Order {

    @Id
    @Column(name = "Id")
    private int Id;
    @Column(name = "Type")
    private String Type;
    @Column(name = "Car_Id")
    private Car CarId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Car getCarId() {
        return CarId;
    }

    public void setCarId(Car carId) {
        CarId = carId;
    }

    public Order(String type, Car carId) {
        Type = type;
        CarId = carId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id=" + Id +
                ", Type='" + Type + '\'' +
                ", CarId=" + CarId +
                '}';
    }
}
