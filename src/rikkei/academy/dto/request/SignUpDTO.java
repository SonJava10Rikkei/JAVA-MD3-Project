package rikkei.academy.dto.request;

import java.util.HashSet;
import java.util.Set;

public class SignUpDTO {
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<String> strRole = new HashSet<>();

    public SignUpDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SignUpDTO(int id, String name, String username, String email, String password, Set<String> strRole) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.strRole = strRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return strRole;
    }

    public void setRole(Set<String> strRole) {
        this.strRole = strRole;
    }
}
