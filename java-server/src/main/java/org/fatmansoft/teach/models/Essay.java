package org.fatmansoft.teach.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "essay",
        uniqueConstraints = {
        })
public class Essay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer essayId;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    private String book;

    @Size(max = 200)
    private String date;

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
