import java.math.BigDecimal;

public class Inventor {
    public long id;
    public String name;
    public String city;
    public BigDecimal networth;

    public Inventor(long id, String name, String city, BigDecimal networth) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.networth = networth;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getNetworth() {
        return networth;
    }

    public String getCity() {
        return city;
    }
}
