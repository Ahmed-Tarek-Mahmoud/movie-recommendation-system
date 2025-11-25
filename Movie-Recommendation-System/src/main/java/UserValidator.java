public interface UserValidator {

    default void ValidUser(User user){
        UserNameValid(user.getUserName());
        UserIdValid(user.getUserId());
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
    default void UserIdValid(String userId){
        if (userId == null) {
            throw new NullPointerException("User id cannot be null");
        }
        if(userId.length() != 9){
            throw new AppExceptions("User id length must equal 9", ErrorCode.USER_ID_LENGTH_ERROR);
        }
        for(int i=0;i<userId.length();i++){
            if( (i==8 && !Character.isLetterOrDigit(userId.charAt(i)) ) || (i!=8 && !Character.isDigit(userId.charAt(i))))
                throw new AppExceptions("User id must contain only digits and might end with char", ErrorCode.USER_ID_ERROR);
        }
    }
}
