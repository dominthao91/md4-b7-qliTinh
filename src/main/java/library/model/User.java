package library.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Component
@Entity
@Table(name = "user")
public class User implements Validator {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
@NotEmpty
@Size(min = 6,max = 20)
private String account;
@NotEmpty
@Size(min = 6,max = 20)
private String password;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Roles roles;

    public User() {
    }

    public User(Long id, String account, String password, Customer customer, Roles roles) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.customer = customer;
        this.roles = roles;
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
User user = (User) target;
String account = user.getAccount();
String password = user.getPassword();
        ValidationUtils.rejectIfEmpty(errors,"account","account.empty");
        ValidationUtils.rejectIfEmpty(errors,"password","password.empty");
        if (account.length()>20||account.length()<6){
            errors.rejectValue("account","account.length");
        }
        if (!account.matches("/@([A-Za-z0-9])")){
errors.rejectValue("account","account.matches");
        }
        if (password.length()>20||password.length()<6){
            errors.rejectValue("password","password.length");
        }

        if (!password.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$\n"));
        errors.rejectValue("password","password.matches");

//        Ít nhất 8 ký tự
//        Chứa ít nhất một chữ số
//        Chứa ít nhất một ký tự alpha dưới và một ký tự alpha trên
//        Chứa ít nhất một ký tự trong một tập hợp các ký tự đặc biệt ( @#%$^v.v.)
//        Không chứa khoảng trắng, tab, v.v.
    }
}
