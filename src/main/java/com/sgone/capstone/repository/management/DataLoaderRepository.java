package com.sgone.capstone.repository.management;

import com.sgone.capstone.model.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLoaderRepository extends JpaRepository<ApplicationUser, Long> {


//    @Query(
//            value = "INSERT INTO " +
//                    "APPLICATION_USER " +
//                    "(username, password_hash, is_admin, is_owner, email, mobile) " +
//                    "VALUES" +
//                    "('admin_1', 'hash', true, false, 'admin_1@email.com', '1')",
//            nativeQuery = true
//    )
//    void loadNewUser();

}
