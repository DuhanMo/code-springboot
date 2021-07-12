package org.zerock.guestbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("/")
    public String index() {
        log.info(">>> / mapping");
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list............." + pageRequestDTO);

        model.addAttribute("result", guestbookService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get.........");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);

        Long id = guestbookService.register(dto);

        redirectAttributes.addFlashAttribute("msg", id);

        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long id, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model) {

        log.info("id: " + id);

        GuestbookDTO dto = guestbookService.read(id);

        model.addAttribute("dto", dto);

    }

    @PostMapping("/remove")
    public String remove(long id, RedirectAttributes redirectAttributes) {

        log.info("id: " + id);

        guestbookService.remove(id);

        redirectAttributes.addFlashAttribute("msg", id);

        return "redirect:/guestbook/list";

    }
    @PostMapping("/modify")
    public String modify(GuestbookDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes){
        log.info("modify dto .... >>>" + dto);


        guestbookService.modify(dto);

        redirectAttributes.addAttribute("id", dto.getId());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addFlashAttribute("msg", "세션속 msg");



        return "redirect:/guestbook/read";

    }
}
