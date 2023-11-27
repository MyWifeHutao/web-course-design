package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.models.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Optional<Teacher> findByPersonPersonId(Integer personId);
    Optional<Teacher> findByPersonNum(String num);
    List<Teacher> findByPersonName(String name);
    @Query(value = "from Teacher where ?1='' or person.num like %?1% or person.name like %?1% ")
    List<Teacher> findTeacherListByNumName(String numName);

    @Query(value="select t from Teacher t, User u where u.person.personId = t.person.personId and u.userId=?1")
    Optional<Teacher> findByUserId(Integer userId);

    @Query(value = "from Teacher where ?1='' or person.num like %?1% or person.name like %?1% ",
            countQuery = "SELECT count(studentId) from Teacher where ?1='' or person.num like %?1% or person.name like %?1% ")
    Page<Teacher> findTeacherPageByNumName(String numName, Pageable pageable);
}
