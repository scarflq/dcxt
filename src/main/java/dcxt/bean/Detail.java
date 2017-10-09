package dcxt.bean;


public class Detail {

    private int id;

    private int num;

    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Detail(int id, int num, int price) {
        this.id = id;
        this.num = num;
        this.price = price;
    }

    public Detail() { }
}
