package com.tn.Controller;

import com.tn.Entity.School;
import com.tn.Repository.SchoolRepository;
import com.tn.req.SchoolReq;
import com.tn.req.SchoolRequpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class SchoolController {
    @Autowired
    private SchoolRepository schoolRepo;

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        List<School> schools  =  schoolRepo.findAll();

        return new  ResponseEntity<>(schools, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody SchoolReq schoolReq){
        School school = new School();

        school.setSchoolName(schoolReq.getSchoolName());
        school.setAddress(schoolReq.getAddress());
        school.setNumTeacher(schoolReq.getNumTeacher());

        schoolRepo.save(school);
        log.info("Đã thêm một trường học mới: ", school.getSchoolName());

        return new ResponseEntity<>("Create successlly: "+ school, HttpStatus.OK);
    }
    @PutMapping("school/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody SchoolRequpdate schoolRequpdate) {

        School school = schoolRepo.findById(id).orElse(null);
        System.out.println(school);
        if (school != null){
            school.setSchoolName(schoolRequpdate.getSchoolName());
            school.setAddress(schoolRequpdate.getAddress());
            schoolRepo.save(school);
            return new ResponseEntity<>("Update suscesslly:", HttpStatus.OK);
        }
        log.info("Đã cập nhật thông tin của trường học: {}", school.getSchoolName());

        return new ResponseEntity<>("Fail not product with id "+ id, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("school/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        schoolRepo.deleteById(id);

        log.info("Đã xóa trường học có ID: {}", id);

        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

}

