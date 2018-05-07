package comp_431.privacytracking.user;

public class UserEnum {

    public int valueFromString(String field) {
        return returnValue(field);
    }

    public String StringFromValue(int value) {
        return returnFiel(value);
    }

    private String returnFiel(int value) {
        switch (value) {
            case 0:
                return "userEmail";

            case 1:
                return "userFirstName";

            case 2:
                return "userLastName";

            case 3:
                return "userAddress";

            case 4:
                return "userCity";

            case 5:
                return "userCountry";

            case 6:
                return "userZip";

        }
        return null;
    }


    private int returnValue(String field) {
        switch (field) {
            case "userEmail":
                return 0;

            case "userFirstName":
                return 1;

            case "userLastName":
                return 2;

            case "userAddress":
                return 3;

            case "userCity":
                return 4;

            case "userCountry":
                return 5;

            case "userZip":
                return 6;

        }
        return -1;
    }

}
