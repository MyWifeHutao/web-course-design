package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.Active;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.repository.ActiveRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/active")//                    地址。。。。。。。。。。。。。。。。。。。。。。。。。。。。。

public class ActiveController {
    @Autowired
    ActiveRepository activeRepository;
    @Autowired
    StudentRepository studentRepository;

    public synchronized Integer getNewActiveId(){  //synchronized 同步方法
        Integer id = activeRepository.getMaxId();  // 查询最大的id
        if(id == null)
            id = 1;
        else
            id = id+1;
        return id;
    }
//    public Map getMapFromActive(Active a, String cnt) {
//        Map m = new HashMap();
//        if(a == null)
//            return m;
//        m.put("num",cnt);
//        m.put("activeId",a.getActiveId());
//        m.put("SheOrJain",a.getSheOrJain());
//        m.put("hour",a.getHour());
//        m.put("aware",a.getAware());
//        m.put("friend",a.getFriend());
//        Student s = a.getStudent();
//        m.put("studentId",s.getStudentId());
//        return m;
//    }
    public List getActiveMapList(Integer studentId) {
        List dataList = new ArrayList();
        List<Active> sList = activeRepository.findByStudentStudentId(studentId);
        if(sList == null || sList.size() == 0)
            return dataList;
        int cnt = 0;
        for(int i = 0; i < sList.size();i++) {
            Active a= sList.get(i);
            Map m = new HashMap();
            m.put("activeId",a.getActiveId());
            Student s = a.getStudent();
            m.put("studentId",s.getStudentId());
            m.put("SheOrJain",a.getSheOrJain());
            m.put("data",a.getDate());
            m.put("hour",a.getHour());
            m.put("aware",a.getAware());
            m.put("friend",a.getFriend());
            dataList.add(m);
        }
        return dataList;
    }

    @PostMapping("/getActiveList")
    public DataResponse getActiveList(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId = dataRequest.getInteger("studentId");
        List dataList = getActiveMapList(studentId);
        return CommonMethod.getReturnData(dataList);
    }

//    @PostMapping("/getActiveInfo")
//    public DataResponse getActiveInfo(@Valid @RequestBody DataRequest dataRequest) {
//        Integer activeId = dataRequest.getInteger("activeId");
//        Active a = null;
//        Optional<Active> op = activeRepository.findById(activeId);
//        int cnt = 0;
//        a = op.get();
//        return CommonMethod.getReturnData(getMapFromActive(a,"00"+Integer.toString(++cnt)));
//    }

    @PostMapping("/activeDelete")
    public DataResponse activeDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer activeId = dataRequest.getInteger("activeId");
        Optional<Active> op = activeRepository.findById(activeId);
        Active a = op.get();
        activeRepository.delete(a);
        return CommonMethod.getReturnMessageOK();  //通知前端操作正常
    }

    @PostMapping("/activeEditSave")
    public DataResponse activeEditSave(@Valid @RequestBody DataRequest dataRequest) {
        Integer activeId = 0;
        Integer studentId =dataRequest.getInteger("studentId");
        String SheOrJain = dataRequest.getString("SheOrJain");
        String hour = dataRequest.getString("hour");
        String data = dataRequest.getString("data");
        String aware = dataRequest.getString("aware");
        String friend = dataRequest.getString("friend");
        Active a = null;
        Student s = studentRepository.findById(studentId).get();

        a = new Active();
        a.setStudent(s);
        a.setActiveId(getNewActiveId());
        activeRepository.saveAndFlush(a);

        a.setAware(aware);
        a.setFriend(friend);
        a.setHour(hour);
        a.setDate(data);
        a.setSheOrJain(SheOrJain);
        activeRepository.save(a);
        return CommonMethod.getReturnData(a.getActiveId());  // 将Id返回前端
    }
}
