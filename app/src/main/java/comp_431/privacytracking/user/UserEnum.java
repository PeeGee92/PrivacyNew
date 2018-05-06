package comp_431.privacytracking.user;

public class UserEnum {

    public UserEnum(String field) {
        returnValue(field);
    }

    private int returnValue(String field) {
        switch (field) {
            case "userEmail":
                return 0;
                break;
            case "userFirstName":
                break;
            case "userLastName":
                break;
            case "userAddress":
                break;
            case "userCity":
                break;
            case "userCountry":
                break;
            case "userZip":
                break;
        }
    }
}
