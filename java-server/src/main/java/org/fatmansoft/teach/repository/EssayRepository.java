package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Active;
import org.fatmansoft.teach.models.Essay;
import org.fatmansoft.teach.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssayRepository extends JpaRepository<Essay,Integer> {
    @Query(value = "select max(essayId) from Essay  ")
    Integer getMaxId();
    //
//    @Query(value = "from Essay a where ?1='' or a.SheOrJain like %?1%")
//    List<Essay> findActiveListBySheOrJain(String SheOrJain);

    List<Essay> findByTeacherTeacherId(Integer teacherId);
}
