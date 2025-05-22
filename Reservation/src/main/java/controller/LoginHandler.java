/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.UserModel;
import protocol.Request;
import protocol.Response;
import protocol.User;


/**
 *
 * @author 00rya
 */
public class LoginHandler {
    private UserModel userModel;

    public LoginHandler() {
        this.userModel = new UserModel(); // 유저 정보 로드
    }

    /**
     * 로그인 요청을 처리하는 메서드
     */
    public Response handle(Request request) {
    User loginAttempt = (User) request.getData();

    User authenticatedUser = userModel.authenticate(
        loginAttempt.getUsername(),
        loginAttempt.getPassword()
    );

    if (authenticatedUser != null) {
        return new Response(true, "로그인 성공", authenticatedUser);
    } else {
        return new Response(false, "아이디 또는 비밀번호가 틀렸습니다.", null);
    }
}
}