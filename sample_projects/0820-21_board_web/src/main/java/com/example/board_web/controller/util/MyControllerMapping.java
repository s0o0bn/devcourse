package com.example.board_web.controller.util;

import com.example.board_web.controller.BoardController;
import com.example.board_web.controller.MainController;
import com.example.board_web.controller.MemberController;
import com.example.board_web.controller.MyController;

public class MyControllerMapping {
    private final MainController mainController = new MainController();
    private final BoardController boardController = new BoardController();
    private final MemberController memberController = new MemberController();

    public MyController getController(String command) {
        if (command.startsWith("/board")) return boardController;
        if (command.startsWith("/member")) return memberController;
        return mainController;
    }
}
