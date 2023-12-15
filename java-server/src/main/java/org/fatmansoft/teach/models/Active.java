package org.fatmansoft.teach.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "active",
        uniqueConstraints = {
        })
public class Active {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activeId;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    private String SheOrJain;//社会实践or课外活动

    @Size(max = 50)
    private String hour;      //时长

    @Size(max = 50)
    private String aware;    //奖励

    @Size(max = 200)
    private String friend;// 队友
    @Size(max = 200)
    private String date;

    public void setActiveId(Integer activeId) {
        activeId = activeId;
    }

    public Integer getActiveId() {
        return activeId;
    }
    public Student getStudent() {
        return student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSheOrJain(String sheOrJain) {
        SheOrJain = sheOrJain;
    }

    public String getAware() {
        return aware;
    }

    public String getHour() {
        return hour;
    }

    public String getSheOrJain() {
        return SheOrJain;
    }

    public void setAware(String aware) {
        this.aware = aware;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getFriend() {
        return friend;
    }
}
