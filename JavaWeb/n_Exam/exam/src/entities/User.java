package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonGender gender;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    public User() {  }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonGender getGender() {
        return gender;
    }

    public void setGender(PersonGender gender) {
        this.gender = gender;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("<div class=\"col-md-3 d-flex flex-column text-center\">")
                .append("<img src=\""
                        + PersonGender.getSimpleValue(this.getGender()) + ".png\" class=\"img-thumbnail mx-auto\" width=\"200\" height=\"200\">")
                .append("<h5 class=\"text-center\">" + this.getUsername() + "</h5>")
                .append("<form method=\"POST\" action=\"/add/friend/" + this.getUsername() + "\"><input type=\"submit\" class=\"btn btn-info add-friend-btn\" value=\"Add Friend\" /></form>")
                .append("</div>");

        return result.toString();
    }
}
