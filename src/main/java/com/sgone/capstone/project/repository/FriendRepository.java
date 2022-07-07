package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query(value = "SELECT * FROM friend_a WHERE id = ?", nativeQuery = true)
    Friend findFriend_a(Long id);

    @Query(value = "SELECT * FROM friend_b WHERE id = ?", nativeQuery = true)
    Friend findFriend_b(Long id);

    @Query(value = "DELETE * FROM friend WHERE id = ?", nativeQuery = true)
    String deleteFriendById(Long id);

//    @Query(value = "DELETE * FROM friend WHERE username_a = ?1 AND username_b = ?2", nativeQuery = true)
//    String deleteFriendPair(String username_a, String username_b);

    @Query(value = "SELECT * FROM friend WHERE username_a =?1 AND username_b = ?2", nativeQuery = true)
    Friend findFriendPairUsername(String username_a, String username_b);

    @Query(value = "SELECT \n" +
            "CASE\n" +
            "\tWHEN friend_a_id = :USER_ID THEN friend_b_id\n" +
            "\tWHEN friend_b_id = :USER_ID THEN friend_a_id\n" +
            "END\n" +
            "FROM friend \n" +
            "INNER JOIN application_user\n" +
            "ON friend_b_id = application_user.id\n" +
            "WHERE friend_a_id = :USER_ID OR friend_b_id = :USER_ID " +
            "ORDER BY friend.id", nativeQuery = true)
    List<Long> findFriendsByID(@Param("USER_ID") Long userID);

    @Query(value= "SELECT id FROM \n" +
            "application_user \n" +
            "WHERE NOT id IN (SELECT \n" +
            "CASE\n" +
            "\tWHEN friend_a_id = ?1 THEN friend_b_id\n" +
            "\tWHEN friend_b_id = ?1 THEN friend_a_id\n" +
            "END\n" +
            "FROM friend \n" +
            "INNER JOIN application_user\n" +
            "ON friend_b_id = application_user.id\n" +
            "WHERE friend_a_id = ?1 OR friend_b_id = ?1)\n" +
            "AND username LIKE ?2 AND NOT ?1 = id  \n" +
            "LIMIT 10", nativeQuery = true)
    List<Long> top10NonFriendsBySearch(Long userID, String search);


}

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend