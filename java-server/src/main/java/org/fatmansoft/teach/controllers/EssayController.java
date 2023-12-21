package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.Essay;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.models.Teacher;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.payload.response.OptionItem;
import org.fatmansoft.teach.payload.response.OptionItemList;
import org.fatmansoft.teach.repository.EssayRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.repository.TeacherRepository;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/essay")//                    地址。。。。。。。。。。。。。。。。。。。。。。。。。。。。。

public class EssayController {
    @Autowired
    EssayRepository essayRepository;
    @Autowired
    TeacherRepository teacherRepository;

    public synchronized Integer getNewEssayId(){  //synchronized 同步方法
        Integer id = essayRepository.getMaxId();  // 查询最大的id
        if(id == null)
            id = 1;
        else
            id = id+1;
        return id;
    }
    //    public Map getMapFromessay(essay a, String cnt) {
//        Map m = new HashMap();
//        if(a == null)
//            return m;
//        m.put("num",cnt);
//        m.put("essayId",a.getessayId());
//        m.put("SheOrJain",a.getSheOrJain());
//        m.put("hour",a.getHour());
//        m.put("aware",a.getAware());
//        m.put("friend",a.getFriend());
//        Student s = a.getStudent();
//        m.put("studentId",s.getStudentId());
//        return m;
//    }
    public List getEssayMapList(Integer teacherId) {
        List dataList = new ArrayList();
        List<Essay> sList = essayRepository.findByTeacherTeacherId(teacherId);
        if(sList == null || sList.size() == 0)
            return dataList;
        int cnt = 0;
        for(int i = 0; i < sList.size();i++) {
            Essay a= sList.get(i);
            Map m = new HashMap();
            m.put("essayId",a.getEssayId());
            Teacher s = a.getTeacher();
            m.put("teacherId",s.getTeacherId());
            m.put("data",a.getDate());
            m.put("book",a.getBook());
            dataList.add(m);
        }
        return dataList;
    }
    @PostMapping("/getTeacherItemOptionList")
    public OptionItemList getTeacherItemOptionList(@Valid @RequestBody DataRequest dataRequest) {
        List<Teacher> sList = teacherRepository.findTeacherListByNumName("");  //数据库查询操作
        OptionItem item;
        List<OptionItem> itemList = new ArrayList();
        for (Teacher s : sList) {
            itemList.add(new OptionItem( s.getTeacherId(),s.getTeacherId()+"", s.getPerson().getNum()+"-"+s.getPerson().getName()));
        }
        return new OptionItemList(0, itemList);
    }

    @PostMapping("/getEssayList")
    public DataResponse getEssayList(@Valid @RequestBody DataRequest dataRequest) {
        Integer teacherId = dataRequest.getInteger("teacherId");
        List dataList = getEssayMapList(teacherId);
        return CommonMethod.getReturnData(dataList);
    }

//    @PostMapping("/getessayInfo")
//    public DataResponse getessayInfo(@Valid @RequestBody DataRequest dataRequest) {
//        Integer essayId = dataRequest.getInteger("essayId");
//        essay a = null;
//        Optional<essay> op = essayRepository.findById(essayId);
//        int cnt = 0;
//        a = op.get();
//        return CommonMethod.getReturnData(getMapFromessay(a,"00"+Integer.toString(++cnt)));
//    }

    @PostMapping("/EssayDelete")
    public DataResponse essayDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer essayId = dataRequest.getInteger("essayId");
        Optional<Essay> op = essayRepository.findById(essayId);
        Essay a = op.get();
        essayRepository.delete(a);
        return CommonMethod.getReturnMessageOK();  //通知前端操作正常
    }

    @PostMapping("/essayEditSave")
    public DataResponse essayEditSave(@Valid @RequestBody DataRequest dataRequest) {
        Integer essayId = 0;
        Integer teacherId =dataRequest.getInteger("teacherId");
        String book = dataRequest.getString("book");
        String data = dataRequest.getString("data");
        Essay a = null;
        Teacher s = teacherRepository.findById(teacherId).get();

        a = new Essay();
        a.setTeacher(s);
        a.setEssayId(getNewEssayId());
        essayRepository.saveAndFlush(a);

        a.setBook(book);
        a.setDate(data);
        essayRepository.save(a);
        return CommonMethod.getReturnData(a.getEssayId());  // 将Id返回前端
    }
}
