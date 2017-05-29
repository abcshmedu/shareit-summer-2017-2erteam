package datenzugriffsschicht;

/**
 * Represents an user in the system.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class User {
    private String name;
    private String password;
    private int id;
    private boolean active;
    private String email;
    private UserGroup group;
    
    /**
     * Default constructor.
     */
    User() {
        
    }
    
    /**
     * constructs an user.
     * @param name of the user
     * @param password of the user
     * @param id of the user
     * @param active true if the user is activated
     * @param email from the user
     * @param group usergroup from the user
     */
    public User(String name, String password, int id, boolean active, String email, UserGroup group) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.active = active;
        this.email = email;
        this.group = group;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }
    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the group
     */
    public UserGroup getGroup() {
        return group;
    }
    /**
     * @param group the group to set
     */
    public void setGroup(UserGroup group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }
    
    //CHECKSTYLE:OFF
    @Override
    //CHECKSTYLE:ON
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (active != other.active) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (group != other.group) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        return true;
    }
        
}
