package home.vu.ercommerce.common.enums;

/**
 * types of users
 * 
 * @author Le Huy Vu
 */
public enum UserRole {
    ROLE_USER, ROLE_ADMIN;

    public static UserRole getType(String type) {
        if (type != null) {
            for (UserRole role : UserRole.values()) {
                if (type.equals(role.name())) {
                    return role;
                }
            }
        }
        return null;
    }
}
