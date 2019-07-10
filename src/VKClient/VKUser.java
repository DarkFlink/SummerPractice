package VKClient;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VKUser {
    public int userId;
    public String firstName;
    public String lastName;
    private ListIterator<VKUser> iter;
    /*public boolean is_closed;
    public boolean can_access_closed;
    public int sex;
    public String bdate;
    public String university_name;
    public String faculty_name;
    public Place city;
    public Place country; */

    public VKUser(
            int userId,
            String firstName,
            String lastName)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + userId;
    }

    @Override
    public boolean equals(Object obj) {
        VKUser tmp = (VKUser)obj;
        if (tmp.userId == this.userId)
            return true;
        return false;
    }

    public static String[] arrayToStrings(ArrayList<VKUser> list)
    {
        String[] result = new String[list.size()];
        for(int i=0; i < list.size(); i++)
            result[i] = list.get(i).toString();
        return result;
    }

    public static void findAndRemove(ArrayList<VKUser> list, int id) throws NoSuchElementException
    {
        int i=0;
        VKUser tmp = new VKUser( id, null, null );
        for(ListIterator<VKUser> iter = list.listIterator(i); iter.hasNext(); iter = list.listIterator(i++) )
        {
            VKUser el = iter.next();
            if (el.equals(tmp))
            {
                iter.remove();
                return;
            }
        }
        throw new NoSuchElementException("No such user with id: " + id);
    }
}
