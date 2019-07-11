package VKClient;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
public class VKClientTest {
    private static final String[] requestArgs = {"photo50", "education"};
    public static final String[] basicArgs = {"id", "first_name", "last_name", "deactivated", "is_closed", "photo_50"};
    private int userID = 206043986;
    @Test
        public void parseFriendsJson() {
        VKClient user = new VKClient();
        String response = user.getUserFriends(userID, basicArgs);
        ArrayList<VKUser> list = new ArrayList<>();
        list = user.parseFriendsJson(response);
        try {
            File test = new File(new File("").getAbsolutePath()  + "\\tests\\VKClient\\DataSets\\test1.txt");
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
        String response = user.getUserFriends(userID, basicArgs);
        ArrayList<VKUser> list;
        list = user.parseFriendsJson(response);
        for(int i = 0; i< list.size();i++){
            try {
                VKUser tmp = list.get(i);
                String[] photo = {"photo_50"};
                System.out.print(user.getUser(Integer.toString(tmp.userId), photo).toString() + " ");
            }catch (Throwable e){
                System.out.print("ERROR" + e);
            }
            System.out.println();

        }
    }

    @Test
    public void getCommonFriends() {
        try {
            userID = 206043986;
            VKClient user = new VKClient();
            File test = new File(new File("").getAbsolutePath()  + "\\tests\\VKClient\\DataSets\\test2.txt");
            BufferedReader br;
            FileReader fileReader = new FileReader(test);
            br = new BufferedReader(fileReader);
            if (!test.exists()) {
                System.out.println("File doesn't exists");
                return;
            }
            ArrayList<String> id = new ArrayList<>();
            String str;
            while( (str = br.readLine()) != null){
                id.add(str);
            }
            int[] target = new int[id.size()];
            for(int i =0;i < id.size();i++) {
                target[i] = Integer.parseInt(id.get(i));
            }
            ArrayList<Integer> common = user.getCommonFriends(userID, target);
            String[] photo = {"photo_50"};
            for(int i = 0; i < common.size();i++){
                System.out.println(user.getUser(Integer.toString(target[i]),photo) + " - " + user.getUser(Integer.toString(userID),photo) + " in common: " + common.get(i));
            }
        }
        catch(Throwable e) {
            System.out.println("ERROR: " + e);
        }
    }
}


