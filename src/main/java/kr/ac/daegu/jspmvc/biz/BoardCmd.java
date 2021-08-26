package kr.ac.daegu.jspmvc.biz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * *.bbs 로 들어오는 모든 요청의 비즈니스 로직을 담당.
 * */
public interface BoardCmd {
    public boolean execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
