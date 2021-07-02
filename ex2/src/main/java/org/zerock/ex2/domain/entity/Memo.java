package org.zerock.ex2.domain.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String memoText;



}
