package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Board;
import com.boot.stickershop.domain.BoardCategory;
import com.boot.stickershop.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest // 내부적으로 @Transactional을 가짐.
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository; // test할 대상을 선언

    @Autowired
    BoardCategoryRepository boardCategoryRepository;
    BoardCategory boardCategory;

    @Autowired
    UserRepository userRepository;
    User user;

    @Test
    public void  testAddBoard() throws Exception {
        Board board = new Board();
        board.setBoardCategory(boardCategory);
        board.setUser(user);
        board.setContent("Hello Spring Boot");
        board.setEdittime(LocalDateTime.now());
        board.setHit(0);
        board.setTitle("Hello");

        Board saveBoard = boardRepository.save(board);
        System.out.println(saveBoard.getId()); // Assert를 이용한 비교문이 들어가야한다.
    }

}
