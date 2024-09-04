package com.example.jpa.controller;

import com.example.jpa.domain.dto.BoardDTO;
import com.example.jpa.domain.dto.FileDTO;
import com.example.jpa.domain.dto.NewBoardDTO;
import com.example.jpa.global.util.FileUtil;
import com.example.jpa.global.web.LoginUser;
import com.example.jpa.service.BoardService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("")
    public ModelAndView list(Pageable page) {
        ModelAndView mav = new ModelAndView("list"); // /WEB-INF/views/list.jsp
        mav.addObject("pageData", boardService.getBoards(page)); // 현재 페이지를 기준으로 하단의 페이지 링크 정보와 보여질 게시글 목록 데이터까지 다 담아뒀음.
        return mav;
    }

    @GetMapping("/write")
    public String write(){ // 로그인 안된 사용자는 글쓰기 못하게 하고 싶음.
        return "write_form";
    }

    @PostMapping()
    public ModelAndView write(NewBoardDTO board,
                              @LoginUser String user,
                              @RequestPart(value="uploadFile", required = false) MultipartFile[] uploadFile) {// 로그인 안된 사용자는 글쓰기 못하게 하고 싶음.
        List<FileDTO> savedFiles = FileUtil.saveFiles(uploadFile);
        boardService.createBoardWithFiles(board, user, savedFiles);

        ModelAndView mav = new ModelAndView("alert"); // /WEB-INF/views/list.jsp
        mav.addObject("msg", "write success");
        mav.addObject("path", "/board");

        return mav;
    }

    @GetMapping("/{no}")
    public ModelAndView read(@PathVariable("no") int no) {
        ModelAndView mav = new ModelAndView("view");
        mav.addObject("bbb",boardService.getBoard(no));
        return mav;
    }

    @GetMapping("/download")
    public void download(@RequestParam("fno")int fno, HttpServletResponse response) throws IOException {
        FileDTO fileDTO = boardService.getFileInfo(fno); // originalFile, savedPath 정보 조회함.

        String fileName = new String(fileDTO.originalName().getBytes("UTF-8"), "ISO-8859-1");

        // response는 기본적으로 html을 응답하는 헤더가 설정되어 있음.
        // 하지만, 지금은 html을 응답하는게 아니라 파일 그 자체를 전송할거임. 응답 객체의 헤더정보를 파일전송 버전으로 변경하자!
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\""); // filename="right.png"
        response.setHeader("Content-Transfer-Encoding", "binary");

        FileInputStream fis = new FileInputStream(fileDTO.savedPath());
        OutputStream os = response.getOutputStream(); // response로 응답하는 스트림(문자열 단위 아니고 바이트 단위로 보내게 됨.)
        FileCopyUtils.copy(fis, os);
    }
}
