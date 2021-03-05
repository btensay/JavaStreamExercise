import java.math.BigDecimal;

public class InventorNetworth {
    public long id;
    public String name;
    public BigDecimal networth;

    public InventorNetworth(long id, String name, BigDecimal networth) {
        this.name = name;
        this.id = id;
        this.networth = networth;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getNetworth() {
        return networth;
    }
}
