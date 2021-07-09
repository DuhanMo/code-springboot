package org.zerock.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;

@SpringBootTest
public class GuestbookServiceTest {

    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister() {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title....")
                .content("Sample Content ...")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(31)
                .size(10)
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> pageResultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: "+pageResultDTO.isPrev());
        System.out.println("NEXT: "+pageResultDTO.isNext());
        System.out.println("TOTAL: "+pageResultDTO.getTotalPage());
        System.out.println("---------------------------------------");
        pageResultDTO.getDtoList().forEach(System.out::println);
        System.out.println("=======================================");
        pageResultDTO.getPageList().forEach(System.out::println);


    }
}
