package com.its.board.repository;

import com.its.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // native sql: update board_table set boardHits = boardHits + 1 where id=?
    // jpql(java persistence query language): 스프링에서 지원해주는 native query
    // 변수명은 entity의 변수명 기준(entity를 타고 가기 때문)
    // 별칭(b)을 반드시 지정
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
    void boardHits(@Param("id") Long id);


}
