/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package protocol;

import java.io.Serializable;

public class User implements Serializable {
    private String username;  // 사용자 ID
    private String password;  // 비밀번호
    private String role;      // 역할: 학생(s), 교수(p), 조교(a)
    private String name;      // 이름

    // 기본 생성자 (직렬화를 위해 필요)
    public User() {}

    // 생성자
    public User(String username, String password, String role, String name) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    // --- Getter / Setter ---
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // --- toString() (디버깅용) ---
    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", name='" + name + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}