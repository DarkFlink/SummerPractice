package VKClient;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VKUser {
    public int userId;
    public String firstName;
    public String lastName;
    public String photoUrl;
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
            String lastName,
            String url)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoUrl = url;
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
}
