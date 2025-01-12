package com.fuyuzhen.towatalk_backend.repository;

import com.fuyuzhen.towatalk_backend.entity.AddFriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddFriendListRepository extends JpaRepository<AddFriendList, Long> {

    AddFriendList findBySendUserIdAndReceiveUserIdAndIsReceive(Long sendUserId,Long receiveUserId, Integer isReceive);

    String selectByReceiveUserIdAndIsReceiveOrderBySendTimeDescStr = "SELECT\n" +
            "    `user`.`user_name`\n" +
            "    , `user`.`sex`\n" +
            "    , `user`.`avatar`\n" +
            "    , `add_friend_list`.`message`\n" +
            "    , `add_friend_list`.`is_receive`\n" +
            "    , `add_friend_list`.`send_time`\n" +
            "    , `add_friend_list`.`id`\n" +
            "FROM\n" +
            "    `towatalkdb`.`user`\n" +
            "    INNER JOIN `towatalkdb`.`add_friend_list` \n" +
            "        ON (`user`.`id` = `add_friend_list`.`send_user_id`)\n" +
            "WHERE `add_friend_list`.`receive_user_id` = ?1 AND `add_friend_list`.`is_receive` IS FALSE \n" +
            "ORDER BY `add_friend_list`.`send_time` DESC;";
    @Query(value = selectByReceiveUserIdAndIsReceiveOrderBySendTimeDescStr, nativeQuery=true)
    List<Object[]> selectByReceiveUserIdAndIsReceiveOrderBySendTimeDesc(Long userId);
}
