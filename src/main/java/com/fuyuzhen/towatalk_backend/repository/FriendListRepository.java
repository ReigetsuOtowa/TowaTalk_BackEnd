package com.fuyuzhen.towatalk_backend.repository;

import com.fuyuzhen.towatalk_backend.entity.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long> {

    String isFriendStr = "SELECT COUNT(1) FROM friend_list WHERE (friend_user_id=?1 AND user_id=?2) " +
            "OR (friend_user_id=?2 AND user_id=?1) AND is_delete=0";
    @Query(value = isFriendStr, nativeQuery = true)
    int isFriend(Long sendUserId, Long receiveUserId);

    String selectAllFriendByUserIdStr = "SELECT\n" +
            "    `user`.`id`\n" +
            "    , `user`.`avatar`\n" +
            "    , `user`.`is_online`\n" +
            "    , `user`.`sex`\n" +
            "    , `user`.`password`\n" +
            "    , `user`.`user_name`\n" +
            "    , `friend_list`.`friend_nick_name`\n" +
            "FROM\n" +
            "    `towatalkdb`.`friend_list`\n" +
            "    INNER JOIN `towatalkdb`.`user` \n" +
            "        ON (`friend_list`.`friend_user_id` = `user`.`id`)\n" +
            "WHERE friend_list.user_id = ?1";
    @Query(value = selectAllFriendByUserIdStr, nativeQuery = true)
    List<Object[]> selectAllFriendByUserId(Long userId);

    String selectAllUnFriendByUserIdStr = "SELECT " +
            "    `user`.`id`\n" +
            "    , `user`.`avatar`\n" +
            "    , `user`.`is_online`\n" +
            "    , `user`.`sex`\n" +
            "    , `user`.`password`\n" +
            "    , `user`.`user_name`\n" + " FROM user WHERE id NOT IN (SELECT friend_user_id FROM friend_list WHERE user_id=?1 AND is_delete=FALSE)  AND id != ?1";
    @Query(value = selectAllUnFriendByUserIdStr, nativeQuery = true)
    List<Object[]> selectAllUnFriendByUserId(Long userId);

    String deleteFriendStr = "UPDATE friend_list SET is_delete=0 WHERE (user_id=?1 AND friend_user_id=?2) OR (user_id=?2 AND friend_user_id=?1)";
    @Query(value = deleteFriendStr, nativeQuery = true)
    Integer deleteFriend(Long sendUserId, Long receiveUserId);
}
