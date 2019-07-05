package VKClient;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VKClientTest {
    private static final String[]  requestArgs = {"photo50", "education"};
    @Test
    public void parseFriendsJson(){
        int userID = 543334830;
        VKClient user = new VKClient();
        String response = user.getUserFriends(userID, requestArgs);
        ArrayList<VKUser> list;
        ArrayList<VKUser> testList = new ArrayList<>();
        testList.add(new VKUser(123,"Amir","Gizzatov"));
        list = user.parseFriendsJson(response);
        Test(list,testList);
        /*if(list != null)
            for (VKUser el : list)
                System.out.println(el.toString());
*/

    }


    @Test
    public void getUserFriends() {

    }
    public void Test(ArrayList<VKUser> first,ArrayList<VKUser> second) {
        try {
            assertEquals(first, second);
            System.out.println("The lists are equal");
        }
        catch(Throwable e){
            System.out.println("The lists are not equal");

        }
    }

}
