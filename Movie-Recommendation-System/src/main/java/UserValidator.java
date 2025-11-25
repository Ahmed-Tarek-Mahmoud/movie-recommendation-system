public interface UserValidator {

    default void ValidUser(User user){
        UserNameValid(user.getUserName());
    }

    default void UserNameValid(String userName){
        if (userName == null) {
            throw new NullPointerException("User name cannot be null");
        }
        
        if (userName.isEmpty()) {
            throw new AppExceptions("User name cannot be empty", ErrorCode.USER_NAME_ERROR);
        }
        
        if (userName.charAt(0) == ' ') {
            throw new AppExceptions("User name cannot start with a space", ErrorCode.USER_NAME_ERROR);
        }
        
        // Check if userName contains only alphabetic characters and spaces
        for (char c : userName.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                throw new AppExceptions("User name must contain only alphabetic characters and spaces", ErrorCode.USER_NAME_ERROR);
            }
        }
    }
}
