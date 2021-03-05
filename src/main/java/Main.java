import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] arg){

        List<Inventor> inventors = new ArrayList<>();
        inventors.add(new Inventor(2000, "Albert Sanders", "London", new BigDecimal("4.55")));
        inventors.add(new Inventor(1000, "Benjamin Franklin", "Durham", new BigDecimal("6.55")));
        inventors.add(new Inventor(8000, "Wilbur Wright", "London", new BigDecimal("5.05")));
        inventors.add(new Inventor(3000, "Adam Smith", "Glasgow", new BigDecimal("12.22")));
        inventors.add(new Inventor(4000, "George Stephenson", "Manchester", new BigDecimal("6.55")));
        inventors.add(new Inventor(5000, "Samuel Morse", "Liverpool", new BigDecimal("8")));
        inventors.add(new Inventor(6000, "Mark Twine", "London", new BigDecimal("5.05")));
        inventors.add(new Inventor(7000, "Ben Frank", "Birmingham", new BigDecimal("10.01")));

        // Filtering collections
             // Case: Get the name of inventors that live in London
             List<String> londonerInventors = inventors.stream()
                     .filter(i -> i.city.equals("London"))
                     .sorted(Comparator.comparing(Inventor::getName))
                     .map(Inventor::getName)
                     .collect(toList());

            System.out.println("Inventors who live in London: ");
            londonerInventors.forEach(inventor -> System.out.println("Inventor "+inventor+" lives in London."));
            System.out.println("----");

            //Case: Get distinct list of cities in which inventors live
            System.out.println("Cities in which inventors live:");
            List<String> inventorsHomeCities = inventors.stream()
                    .map(Inventor::getCity)
                    .distinct()
                    .sorted(Comparator.naturalOrder())
                    .collect(toList());

            inventorsHomeCities.forEach(city -> System.out.println(city));
            System.out.println("----");

        //Projection or Mapping
            //Case: create a list of InventorNetworth contains inventor and his/her networth
                System.out.println("List of inventors with his/her networth:");

                List<InventorNetworth> inventorsByWorth = inventors.stream()
                        .map(temp -> {
                            InventorNetworth inventorObj = new InventorNetworth(
                                    9000,
                                    temp.getName(),
                                    temp.getNetworth());
                                    return inventorObj;
                            })
                        .sorted(Comparator.comparing(InventorNetworth::getNetworth).reversed()) //descending order
                        .collect(Collectors.toList());

                inventorsByWorth.forEach(inventorByWorth -> System.out.println(
                        " Name: "+inventorByWorth.name+"\t"+
                        " Worth: "+inventorByWorth.getNetworth()));
                System.out.println("----");

        //Ordering + grouping
            //Case: create a list of InventorNetworth grouped by networth
            System.out.println("List of inventors grouped by networth:");
            AtomicInteger atomicInt = new AtomicInteger();
            Map<BigDecimal, List<InventorNetworth>> inventorsGroupedByWorth = inventors.stream()
                    .map(temp -> {
                        InventorNetworth inventorObj = new InventorNetworth(
                                atomicInt.incrementAndGet(),
                                temp.getName(),
                                temp.getNetworth());
                        return inventorObj;
                    })
                    .sorted(Comparator.comparing(InventorNetworth::getNetworth).reversed())
                    .collect(Collectors.groupingBy(InventorNetworth::getNetworth));

                inventorsGroupedByWorth.forEach((inventorKey, inventorsList )->
                {
                    System.out.println(" Worth: " + inventorKey);
                    inventorsList.forEach(i -> System.out.println(" Name: " + i.getName()));
                    System.out.println("--------------");
                });

                System.out.println("----");

        // Query continuations or chaining
            // Case: create a list of InventorNetworth grouped by net worth and worth more than
            // £6 billion (pretened that the net worths are in £billions)
            AtomicInteger atomicIntId = new AtomicInteger();
            System.out.println("List of inventors worth more than £6 billion grouped by net worth:");
            List<InventorNetworth> inventorsWorthSixOrMore = inventors.stream()
                    .map(temp -> {
                        InventorNetworth inventorObj = new InventorNetworth(
                                atomicIntId.incrementAndGet(),
                                temp.getName(),
                                temp.getNetworth());
                        return inventorObj;
                    })
                    .sorted(Comparator.comparing(InventorNetworth::getNetworth).reversed()) //descending order
                    .collect(Collectors.toList())
                    .stream().filter(w->w.getNetworth().intValue() >= 6).collect(Collectors.toList());

            inventorsWorthSixOrMore.forEach(inventorByWorth -> System.out.println(
                    " Name: "+inventorByWorth.name+"\t"+
                    " Worth: "+inventorByWorth.getNetworth()));
            System.out.println("----");


        //Join operations


        //One-to-one and One-to-many relationships


        //Flattening a sequence of sequences

    }
}
