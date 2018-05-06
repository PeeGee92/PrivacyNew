package comp_431.privacytracking.user;

public class UserEnum {

    public int returnValue(String field) {
        switch (field) {
            case "userEmail":
                return 0;
            case "userFirstName":
                return 1;
            case "userLastName":
                return 2;
            case "userAddress":
                return 3;
            case "userCountry":
                return 4;
            case "userCity":
                return 5;
            case "userZip":
                return 6;
            default:
                return -1;
        }
    }
}
