package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public Long save(BoardDTO boardDTO) {
        return boardRepository.save(BoardEntity.toEntity(boardDTO)).getId();
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity b : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(b));
        }
        return boardDTOList;
    }

    public BoardDTO findById(Long id) {
//        boardRepository.updateHits(id);
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent())
            return BoardDTO.toBoardDTO(optionalBoardEntity.get());
        else
            return null;
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
