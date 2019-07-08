import VKClient.VKClient;
import VKClient.VKUser;
import GUI.*;
import java.util.ArrayList;

public class ApplicationMain {

    private static final String[]  requestArgs = {"photo50", "education"};

    public static void main(String[] args) {
        new Graph();
        VKClient vk = new VKClient();

        // id of user
        int userID = 1411555542;

        // get json
        String response = vk.getUserFriends(userID, requestArgs);
        ArrayList<VKUser> list;
        list = vk.parseFriendsJson(response);// json -> ArrayList

        // this only for result
        if (list != null)
            for (VKUser el : list)
                System.out.println(el.toString());
    }
}
