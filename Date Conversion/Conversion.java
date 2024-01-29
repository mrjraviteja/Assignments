import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Conversion
{
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        String res = s.nextLine();
        try{
            // Converting the given string to date
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/u k:m");
            LocalDateTime date = LocalDateTime.parse(res,format);
            System.out.println("Given string as date in IST: "+date);

            // Figuring out the time past since 01/01/1970 UTC to get Unix Timestamp
            Instant instant = date.toInstant(ZoneOffset.UTC);
            System.out.println("Unix Timestamp: "+instant.getEpochSecond());

            // Converting IST to UTC
            System.out.println("UTC Timestamp: "+date.minusHours(5).minusMinutes(30));
        }
        catch(Exception e)
        {
            System.out.println("Exception: "+e);
        }
        s.close();
    }
}