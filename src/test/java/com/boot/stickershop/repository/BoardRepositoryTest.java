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

    @Before
    public void initTest() throws Exception {
        boardCategory = boardCategoryRepository.getOne(1L);
        user = userRepository.getOne(1L);

        // 3건의 Board 엔티티를 가진다.
        addBoard("hello spring boot content", "hello spring");
        addBoard("hello java content", "hello java");
        addBoard("hello jpa content", "hello jpa");
    }
    // 만약 @Test 라고 붙은 메소드가 3개라면 @Before가 붙은 메소드는 몇번 실행될까요?
    // @Before -> @1Test, @Before -> @2Test ....

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

    private void addBoard(String content, String title) {
        Board board = new Board();
        board.setBoardCategory(boardCategory);
        board.setUser(user);
        board.setContent(content);
        board.setEdittime(LocalDateTime.now());
        board.setHit(0);
        board.setTitle(title);

        Board saveBoard = boardRepository.save(board);
        System.out.println(saveBoard.getId()); // Assert를 이용한 비교문이 들어가야한다.
    }

    @Test
    public void testGetBoardId1() throws Exception {
        Board board = boardRepository.getOne(1L);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @Test
    public void testGetBoardId2() throws Exception {
        Board board = boardRepository.findBoardById(1L);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    // 제목에 llo가 포함된 목록을 구하는 메소드를 boardRepository에 추가하시오.
    @Test
    public void testFindBoardByTitleContaining() throws Exception {
        List<Board> boardList = boardRepository.findBoardByTitleContaining("llo");
        for(Board b : boardList) {
            System.out.println(b.getTitle());
        }
    }

    // @Query를 이용해서 id가 일치하는 Board를 반환하는 메소드를 BoardRepository에 추가하시오.
    @Test
    public void testQueryFindBoardById() throws Exception {
        Board board = boardRepository.queryFindBoardById(1L);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @Test
    public void testQueryFindByContentContaining() throws Exception {
        List<Board> boardList = boardRepository.queryFindByContentContaining("llo");
        for(Board b : boardList) {
            System.out.println(b.getTitle());
        }
    }
}
