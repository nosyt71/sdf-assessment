import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static final int COL_APP = 0;
    public static final int COL_CAT = 1;
    public static final int COL_RAT = 2;

    public static void main(String[] args) throws Exception {
        
        if (args.length <= 0) {
            System.err.println("CSV file not found.");
            System.exit(1);
        }

        try (FileReader fr = new FileReader(args[0])) {
        BufferedReader br = new BufferedReader(fr);

        Map<String, CategoryStats> stats = new HashMap<>();
        int totalLinesRead = 0;
        int discardLines = 0;

        br.readLine();
        
        br.lines()
         // .peek(line -> totalLinesRead++)
            .filter(line -> !line.contains("NaN"))
            .map(line -> line.split(",")) 
            .map(cols -> new PlaystoreEntry(cols[COL_APP].trim(), cols[COL_CAT].trim(), Float.parseFloat(cols[COL_RAT].trim()) ))
            .collect(Collectors.groupingBy(entry -> entry.category())) 
            .forEach((String category, List<PlaystoreEntry> entries) -> {
                CategoryStats s = new CategoryStats(category);
                for(PlaystoreEntry e: entries)
                    s.compute(e);
                stats.put(category, s);
            });

            for (String c: stats.keySet()) {
                CategoryStats s = stats.get(c);
                System.out.printf("Category: %s\n Highest: %s, (%f)\n Lowest: %s, (%f)\n Average: %f\n Discarded: %d\n", 
                c, 
                s.getHighestRatedApp(), 
                s.getHighestRating(), 
                s.getLowestRatedApp(), 
                s.getLowestRating(),
                s.averageRating(),
                discardLines);
        }
       
        System.out.println("Total lines read from csv: " + totalLinesRead);
        
    }
}
}

