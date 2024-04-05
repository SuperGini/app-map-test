package org.mihai.repository;

import org.mihai.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
                SELECT u FROM User u WHERE u.username =:username

            """)
    Optional<User> findByUsername(String username);


//    Optional<User> findByUsername(String username);


//    @Query("""
//                    SELECt u FROM User u LEFT JOIN FETCH u.orders
//            """)

    @Query(""" 
            SELECT u FROM User u
            LEFT JOIN FETCH u.orders
            WHERE u.id IN 
                (SELECT u.id FROM User u ORDER BY u.id ASC)
            """)
    Page<User> getUsersPaginated(PageRequest pageRequest);


    @Query("""
            SELECT u.id FROM User u
            """)
    List<Integer> getUsersIdPaginated(PageRequest pageRequest);


    @Query("""
                    SELECT u FROM User u LEFT JOIN FETCH u.orders WHERE u.id IN :usersIds
            """)
    List<User> getUsersPaginated(List<Integer> usersIds);

    @Query("""
                    SELECT count(u.id) FROM User u
            """)
    Long getUsersCount();

    @Modifying
    @Query("DELETE FROM User u WHERE u.username =:username")
    void deleteUserByUsername(String username);


}
