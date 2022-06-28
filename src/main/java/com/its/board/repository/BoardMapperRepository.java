package com.its.board.repository;

import com.its.board.dto.BoardDTO;
import com.its.board.dto.BoardMapperDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // mybatis가 제공하는 어노테이션
public interface BoardMapperRepository {
    List<BoardMapperDTO> boardList();

    void save(BoardMapperDTO boardMapperDTO);

}
