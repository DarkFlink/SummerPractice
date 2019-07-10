package VKClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.google.gson.*;

public class VKClient {

    public static final String[] basicArgs = {"id", "first_name", "last_name", "deactivated", "is_closed"};

    private static final String accessVkApiToken = "key";
    private static final String versionVkApi = "5.101";
    private static final String beginVkApi = "https://api.vk.com/method/";
    private static final String endVkApi = "&access_token=" + accessVkApiToken + "&v=" + versionVkApi;

    public ArrayList<VKUser> parseFriendsJson(String str) {
        try
        {
            JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject)jsonParser.parse(str);
            if (jo.get("error") != null)
            {
                System.out.println("Error code: " + jo.get("error").getAsJsonObject().get("error_code").getAsInt()
                        + "\ndescription: " +
                        jo.get("error").getAsJsonObject().get("error_msg").getAsString());
                return null;
            }


            JsonArray jsonArr = jo.get("response").getAsJsonObject().getAsJsonArray("items");
            ArrayList<VKUser> list = new ArrayList<>();

            for (JsonElement obj : jsonArr)
            {
                JsonObject tmp = obj.getAsJsonObject();
                list.add( new VKUser( tmp.get("id").getAsInt(),
                        tmp.get("first_name").getAsString(),
                        tmp.get("last_name").getAsString()));
            }

            return list;
        }
        catch (Exception e)
        {
            System.out.println("Failed: parseFriendsJson");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<int[]> getCommonFriends(int srcId, int[] targetIds)
    {
        ArrayList<VKUser> srcUser = getFriends(srcId, null, null);
        ArrayList<int[]> cmnFriends = new ArrayList<int[]>();
        for(int it : targetIds)
        {
            ArrayList<VKUser> list = getFriends(it, null, null);
            int count = 0;
            for(VKUser i : list)
                if(srcUser.contains(i))
                    count++;
            int[] ids = {it, count};
            cmnFriends.add(ids);
        }
        return cmnFriends;
    }

    public VKUser getUser(int userId, String[] args)
    {
        String sb = createGetRequest("users.get?user_ids=", userId,null, args);
        String request = getRequest(sb);

        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(request);

        if (jo.get("error") != null)
        {
            System.out.println("Error code: " + jo.get("error").getAsJsonObject().get("error_code").getAsInt()
                    + "\ndescription: " +
                    jo.get("error").getAsJsonObject().get("error_msg").getAsString());
            return null;
        }

        JsonObject response = jo.get("response").getAsJsonArray().get(0).getAsJsonObject();

        return new VKUser(response.get("id").getAsInt(),
                response.get("first_name").getAsString(),
                response.get("last_name").getAsString());
    }

    public String getUserFriends(int userId,  String order, String[] args)
    {
        return getRequest(createGetRequest("friends.get?user_id=", userId, order, args));
    }

    public ArrayList<VKUser> getFriends( int userId, String order, String[] args )
    {
        String res = getUserFriends(userId, order, args);
        return parseFriendsJson(res);
    }

    private String createGetRequest(String method, int id, String order, String[] args)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(beginVkApi + method + id);
        if(order != null)
            sb.append("&order=" + order);
        if(args != null) {
            sb.append("&fields=");
            for (String field : args)
                sb.append(field + ",");
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append(endVkApi);

        return sb.toString();
    }

    private String getRequest(String url)
    {
        StringBuilder sb = new StringBuilder();
        try {
            URL requestUrl=new URL(url);
            URLConnection con = requestUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            int cp;
            while ((cp = in.read()) != -1) {
                sb.append((char) cp);
            }
        }
        catch(Exception e) {
            System.out.println("Failed: getRequest");
        }
        return sb.toString();
    }
}