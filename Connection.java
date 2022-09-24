import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class Connection {
    public static String getConnectionUsers(){

        String result = "";

        try{
            URL url = new URL("https://fakestoreapi.com/users");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();            
            int code = connection.getResponseCode();

            if(code==200){
                InputStream in = new BufferedInputStream(connection.getInputStream());
                if (in != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        result += line;
                }
                in.close();
            }

            result = result.replace("[", "").replace("]", "");

            return result;

        } catch(UnknownHostException e){
            System.out.println("Invalid URL!!");
        } catch (Exception e){
            System.out.println("Connection Fail!");
        }

        return result;
    }
}
