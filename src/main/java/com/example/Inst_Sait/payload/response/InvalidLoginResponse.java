package com.example.Inst_Sait.payload.response;

public class InvalidLoginResponse {
    private String username;
    private String password;

    //Возвращает на клиент эти данные,
    // когда клиент сделал не правильный запрос
    public InvalidLoginResponse(){
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }

}
