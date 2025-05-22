/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package protocol;

import java.io.Serializable;

/**
 *
 * @author 00rya
 */
public class Request implements Serializable {
    private String command; // 예: "LOGIN", "REGISTER", "RESERVE" 등
    private Object data;    // 요청에 필요한 데이터 (User, 예약정보 등)

    public Request() {}

    public Request(String command, Object data) {
        this.command = command;
        this.data = data;
    }

    // --- Getter / Setter ---
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
    
