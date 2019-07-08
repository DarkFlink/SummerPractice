package VKClient;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
public class VKClientTest {
    private static final String[] requestArgs = {"photo50", "education"};

    @Test
    public void parseFriendsJson() {
        int userID = 206043986;
        VKClient user = new VKClient();
        String response = user.getUserFriends(userID, requestArgs);
        ArrayList<VKUser> list = new ArrayList<>();
        list = user.parseFriendsJson(response);
        try {
            File test = new File("test1.txt");
            BufferedReader br = new BufferedReader(new FileReader("test1.txt"));
            if (!test.exists()) {
                System.out.println("File doesn't exists");
                return;
            }
            String line;
            int j = 0;
            while((line = br.readLine()) != null){
                System.out.print(line);
                System.out.print(" - ");
                System.out.print(list.get(j).toString());
                System.out.print(" - ");
                if(list.get(j).toString().equals(line)){
                    System.out.println("equals");
                }
                ++j;
            }
        }catch(Throwable e){
            System.out.println("ERROR" + e);
        }
    }
}


