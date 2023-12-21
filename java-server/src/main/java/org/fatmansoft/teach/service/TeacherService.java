package org.fatmansoft.teach.service;

import org.fatmansoft.teach.models.Person;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.models.Teacher;
import org.fatmansoft.teach.models.Team;
import org.fatmansoft.teach.util.ComDataUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherService {
    public Map getMapFromTeacher(Teacher teacher) {
        Map m = new HashMap();
        Person p;
        if(teacher == null)
            return m;
        m.put("title",teacher.getTitle());
        m.put("degree",teacher.getDegree());
        m.put("jiang",teacher.getJiang());
        m.put("yan",teacher.getYan());
        p = teacher.getPerson();
        if(p == null)
            return m;
        m.put("teacherId", teacher.getTeacherId());
        m.put("personId", p.getPersonId());
        m.put("num",p.getNum());
        m.put("name",p.getName());
        m.put("dept",p.getDept());
        m.put("card",p.getCard());
        String gender = p.getGender();
        m.put("gender",gender);
        m.put("genderName", ComDataUtil.getInstance().getDictionaryLabelByValue("XBM", gender)); //性别类型的值转换成数据类型名
        m.put("birthday", p.getBirthday());  //时间格式转换字符串
        m.put("email",p.getEmail());
        m.put("phone",p.getPhone());
        m.put("address",p.getAddress());
        m.put("introduce",p.getIntroduce());
        return m;
    }

}
