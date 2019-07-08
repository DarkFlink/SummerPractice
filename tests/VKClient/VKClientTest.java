package VKClient;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
public class VKClientTest {
    private static final String[] requestArgs = {"photo50", "education"};
    private static final String orderFriends = "name";
    private int userID = 206043986;
    @Test
        public void parseFriendsJson() {
        VKClient user = new VKClient();
        String response = user.getUserFriends(userID, orderFriends, requestArgs);
        ArrayList<VKUser> list = new ArrayList<>();
        list = user.parseFriendsJson(response);
        try {
            File test = new File(new File("").getAbsolutePath()  + "\\tests\\VKClient\\test1.txt");
            BufferedReader br;
            FileReader fileReader = new FileReader(test);
            br = new BufferedReader(fileReader);
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
                else{
                    System.out.println("not equals");
                    return;
                }
                ++j;
            }
        }catch(Throwable e){
            System.out.println("ERROR" + e);
        }
    }

    @Test
    public void getUser() {
        VKClient user = new VKClient();
        userID = 206043986;
        String response = user.getUserFriends(userID, orderFriends, requestArgs);
        ArrayList<VKUser> list;
        list = user.parseFriendsJson(response);
        for(int i = 0; i< list.size();i++){
            VKUser tmp = list.get(i);
            System.out.println(user.getUser(tmp.userId,null));


        }
    }
}


