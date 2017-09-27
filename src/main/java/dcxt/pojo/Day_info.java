package dcxt.pojo;

public class Day_info {
    private String date;

    private Integer pTotal;

    private Integer oTotal;

    private Integer priceTotal;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Integer getpTotal() {
        return pTotal;
    }

    public void setpTotal(Integer pTotal) {
        this.pTotal = pTotal;
    }

    public Integer getoTotal() {
        return oTotal;
    }

    public void setoTotal(Integer oTotal) {
        this.oTotal = oTotal;
    }

    public Integer getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
    }
}