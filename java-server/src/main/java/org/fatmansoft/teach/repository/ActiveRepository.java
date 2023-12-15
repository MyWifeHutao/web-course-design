package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Active;
import org.fatmansoft.teach.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveRepository extends JpaRepository<Active,Integer> {
    @Query(value = "select max(activeId) from Active  ")
    Integer getMaxId();
//
    @Query(value = "from Active a where ?1='' or a.SheOrJain like %?1%")
    List<Active> findActiveListBySheOrJain(String SheOrJain);

    List<Active> findByStudentStudentId(Integer studentId);
}
