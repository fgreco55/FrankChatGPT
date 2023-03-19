import com.lilittlecat.chatgpt.offical.ChatGPT;   // https://github.com/LiLittleCat/ChatGPT
import org.eclipse.jetty.util.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class Main {
    private static String CONFIGFILE = "src/main/java/frank.properties";

    public static void main(String[] args) throws IOException {

        Properties prop = getConfigProperties(CONFIGFILE);
        String key = prop.getProperty("chatgpi.apikey");
        java.util.Scanner userinput;
        ChatGPT chatGPT = new ChatGPT(key);

        while (true) {
            System.out.print("Command> ");
            userinput = new java.util.Scanner(System.in);

            if (userinput.hasNextLine()) {
                String cmd = userinput.nextLine();
                if (!cmd.isEmpty()) {
                    String result = chatGPT.ask(cmd);
                    System.out.println(result.trim());
                }
            }
        }
    }

    static Properties getConfigProperties(String fname) throws IOException {
        Properties prop = new Properties();
        InputStream in = new FileInputStream(fname);

        prop.load(in);

        for (Enumeration e = prop.propertyNames(); e.hasMoreElements(); ) {
            String key = e.nextElement().toString();
            //System.out.println(key + " = " + prop.getProperty(key));
        }
        return prop;
    }
}
