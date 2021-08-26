package kr.ac.daegu.jspmvc.biz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardPwdCheckCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return true;
    }
}