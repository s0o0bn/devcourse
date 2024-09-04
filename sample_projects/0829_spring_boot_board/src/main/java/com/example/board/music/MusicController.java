package com.example.board.music;

import com.example.board.global.pagination.Page;
import com.example.board.global.pagination.PagedItems;
import com.example.board.global.web.LoginUser;
import com.example.board.music.dto.MusicDetailDto;
import com.example.board.music.dto.MusicItemDto;
import com.example.board.music.dto.ModifiableMusicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/musics")
@RequiredArgsConstructor
public class MusicController {
    private final MusicService service;

    @GetMapping("")
    public ModelAndView getMusicListView(Page page) {
        ModelAndView mv = new ModelAndView("musics");
        PagedItems<MusicItemDto> musicPage = service.getMusicList(page);
        mv.addObject("page", musicPage);

        return mv;
    }

    @GetMapping("/new-form")
    public String getMusicRegisterForm() {
        return "register-form";
    }

    @GetMapping("/{no}")
    public ModelAndView getMusicDetails(@PathVariable("no") int no) {
        ModelAndView mv = new ModelAndView("music-detail");
        MusicDetailDto musicDetail = service.getMusicDetail(no);
        mv.addObject("music", musicDetail);

        return mv;
    }

    @GetMapping("/{no}/update-form")
    public ModelAndView getMusicUpdateForm(@PathVariable("no") int no) {
        ModelAndView mv = new ModelAndView("update-form");
        MusicDetailDto musicDetail = service.getMusicDetail(no);
        mv.addObject("music", musicDetail);

        return mv;
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Void> registerMusic(@LoginUser String user, @RequestBody ModifiableMusicDto music) {
        service.register(music, user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{no}")
    @ResponseBody
    public ResponseEntity<Void> updateMusic(@LoginUser String user, @PathVariable("no") int no, @RequestBody ModifiableMusicDto music) {
        service.update(user, no, music);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{no}")
    @ResponseBody
    public ResponseEntity<Void> deleteMusic(@LoginUser String user, @PathVariable int no) {
        service.delete(user, no);
        return ResponseEntity.noContent().build();
    }

}
