package VKClient;

import org.junit.Test;

import java.util.ArrayList;

public class VKClientTest {
    private static final String[] requestArgs = {"photo50", "education"};

    @Test
    public void parseFriendsJson() {
        int userID = 177754919;
        VKClient user = new VKClient();
        String response = user.getUserFriends(userID, requestArgs);
        ArrayList<VKUser> list = new ArrayList<>();
        ArrayList<VKUser> testList = new ArrayList<>();

        testList.add(new VKUser(86251509, "Malika", "Isengeldinova"));
        testList.add(new VKUser(86251509, "Malika", "Isengeldinova"));
        testList.add(new VKUser(255335617, "Dana", "Murtazina"));
        list = user.parseFriendsJson(response);
        for(int i = 0; i < list.size();i++ ){
            System.out.print(list.get(i).toString());
            System.out.print(" - ");
            System.out.print(testList.get(i).toString());
            System.out.print(" - ");
            if(list.get(i).isEquals(testList.get(i))){
                System.out.println("equals");
            }
            else{
                System.out.println("not equals");
                return;
            }
        }
    }


    @Test
    public void getUserFriends() {

    }

    public void Test(Object first, Object second) {
        if(first.equals(second))
            System.out.println("equals");
        else
            System.out.println("not equals");
    }

}

