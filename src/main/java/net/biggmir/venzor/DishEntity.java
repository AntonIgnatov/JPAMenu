package net.biggmir.venzor;

import javax.persistence.*;

@Entity
@Table(name = "meny")
public class DishEntity {
    @Id
    @Column (nullable = false, unique = true)
    private String name;
    @Column (nullable = false)
    private double price;
    @Column (nullable = false)
    private double weight;
    @Column
    private Integer discount;

    public DishEntity() {
    }

    public DishEntity(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("["+this.getName()+" price: "+this.getPrice()+"UAH weight: "+this.getWeight()+"g");
        if(this.getDiscount()!=null){
            sb.append(" discont: "+ this.getDiscount()+"%");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishEntity that = (DishEntity) o;


        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.weight, weight) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return discount != null ? discount.equals(that.discount) : that.discount == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = 1;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }
}
