package VKClient;

import java.util.ArrayList;

public class VKUser {
    public int userId;
    public String firstName;
    public String lastName;
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
        return userId + " " + firstName + " " + lastName;
    }

    public boolean isEquals(Object obj){
        VKUser tmp = (VKUser) obj;
        if(this.firstName.equals(tmp.firstName)){
            return this.userId == tmp.userId;
        }
        return false;
    }

}
