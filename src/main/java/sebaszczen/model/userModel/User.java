package sebaszczen.model.userModel;

import sebaszczen.dto.UserDto;
import sebaszczen.model.Role;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String matchingPassword;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public User() {
    }

    public User(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.password = userDto.getPassword();
        this.matchingPassword = userDto.getMatchingPassword();
        this.email = userDto.getEmail();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
