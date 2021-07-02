package org.zerock.ex2.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex2.domain.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {

}
