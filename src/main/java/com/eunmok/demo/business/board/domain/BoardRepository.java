package com.eunmok.demo.business.board.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface BoardRepository extends Repository<Board, Long> {
    List<Board> findAllByTitleContains(String title);
    Board findById(Long id);
    Board save(Board board);
    void deleteById(Long id);
}
