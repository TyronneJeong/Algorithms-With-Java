package programmers.assignment.kakao2021.controller;

public class MainController {
    private MainController(){}

    public void doGet(String arg){
        System.out.println("doGet!!" + arg);
    }

    public void doPost(String arg){
        System.out.println("doPost!!");
    }
}
