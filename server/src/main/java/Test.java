import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        String text  = "Nikor 19mm\n" +
                "#product_description\n" +
                "As the first PC-NIKKOR to employ two PC rotation mechanisms, this lens takes flexibility to a new level. Ideal for architectural, fine art, and landscape photography, it’s made for photographers who have a keen sense of space.\n" +
                "#price 2,619.43 GBP\n" +
                "#availibility in stock\n" +
                "#brand Nikon\n" +
                "#url https://www.wilkinson.co.uk";


        Pattern pattern = Pattern.compile("(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)");

        Matcher matcher = pattern.matcher(text);
        int lastMatchPos = 0;
        while (matcher.find()){
            if(lastMatchPos ==0 &&  matcher.start() > 0){

            }

            System.out.println(matcher.group(1));

            lastMatchPos = matcher.end();
        }
        if (lastMatchPos != text.length())
            System.out.println("DOESN'T MATCH!");

    }
}
