import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Manipulation 
{
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        String res = s.nextLine();

        try{
            // Converting string to date
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/u k:m");
            LocalDateTime date = LocalDateTime.parse(res,format);

            // List to store all dates
            ArrayList<LocalDateTime> dates = new ArrayList<>();
            dates.add(date);

            // Performing given queries on date
            System.out.println("Given string as date: "+date);
            LocalDateTime date1 = date.plusDays(1);
            dates.add(date1);
            System.out.println("Add one day to date: "+date1);
            LocalDateTime date2 = date.minusDays(10);
            dates.add(date2);
            System.out.println("Subtract 10 days from date: "+date2);
            LocalDateTime date3 = date.plusHours(1);
            dates.add(date3);
            System.out.println("Add one hour to date: "+date3);
            LocalDateTime date4 = date.minusHours(5).minusMinutes(30);
            dates.add(date4);
            System.out.println("Subtract 5 hours 30 mins from date: "+date4); 

            // List before sorting
            System.out.println("ArrayList before sorting: "+dates);
            Collections.sort(dates);
            // List after sorting
            System.out.println("ArrayList after sorting: "+dates);
        
        }
        catch(Exception e)
        {
            System.out.println("Exception: "+e);
        }
        
        s.close();
    }
}
