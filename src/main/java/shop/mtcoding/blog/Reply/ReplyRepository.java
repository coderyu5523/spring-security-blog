package shop.mtcoding.blog.Reply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.board.BoardResponse;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em ;

    public List<BoardResponse.ReplyDTO> findByBoardId(int boardId, User sessionUser){
        String q = """
                select rt.id, rt.user_id, rt.comment, ut.username\s
                                            from reply_tb rt
                                            inner join user_tb ut on rt.user_id = ut.id\s
                                            where rt.board_id = ?                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1,boardId);
        List<Object[]> obs = query.getResultList();
        List<Object[]> rows = query.getResultList();
        return obs.stream().map(ob-> new BoardResponse.ReplyDTO(ob,sessionUser)).toList();

    }

    @Transactional
    public void save(ReplyRequest.WriteDTO requestDTO, int userId) {
        Query query = em.createNativeQuery("insert into reply_tb(comment,board_id,user_id,created_at) values(?,?,?,now()) ");
        query.setParameter(1,requestDTO.getComment());
        query.setParameter(2,requestDTO.getBoardId());
        query.setParameter(3,userId);
        query.executeUpdate();

    }


}
