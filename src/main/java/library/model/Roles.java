package library.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.Validator;


@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String admin;
    private String user;

    public Roles() {
    }

    public Roles(Long id, String admin, String user) {
        this.id = id;
        this.admin = admin;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
