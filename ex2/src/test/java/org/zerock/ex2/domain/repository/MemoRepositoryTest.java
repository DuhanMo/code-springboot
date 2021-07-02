package org.zerock.ex2.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.domain.entity.Memo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import javax.transaction.Transactional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {
    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(">>> " +memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample ... " + i).build();
            memoRepository.save(memo);
        });
    }
    @Test
    public void selectTest() {
        Long id = 100L;

        Memo result = memoRepository.findById(id).orElseThrow(RuntimeException::new);
        System.out.println("=========================");
        System.out.println(">>>" + result);
    }
    @Test
    @Transactional
    public void testSelect2() {
        Long id = 100L;

        Memo memo = memoRepository.getOne(id);

        System.out.println("==================");
        System.out.println(">>>" + memo);


    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().id(100L).memoText("100번 메모 업데이트").build();

        System.out.println(memoRepository.save(memo));
    }
    @Test
    public void deleteAll() {
        memoRepository.deleteById(100L);
    }
    @Test
    public void testPageDefault() {
        // 1페이지 10개
        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(">>> " + result);
    }

}