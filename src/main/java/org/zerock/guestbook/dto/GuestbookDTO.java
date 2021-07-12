package org.zerock.guestbook.dto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate, modDate;
}
