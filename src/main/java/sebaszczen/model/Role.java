package sebaszczen.model;

import sebaszczen.model.userModel.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role() {
    }

    public Role(String name, Collection<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public Collection<User> getUsers() {
        return users;
    }
}
