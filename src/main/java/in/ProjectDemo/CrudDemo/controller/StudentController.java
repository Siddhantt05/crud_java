package in.ProjectDemo.CrudDemo.controller;
import in.ProjectDemo.CrudDemo.dto.StudentRequestDto;
import in.ProjectDemo.CrudDemo.dto.StudentResponseDto;
import in.ProjectDemo.CrudDemo.entity.Student;
import in.ProjectDemo.CrudDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public  class StudentController {
   //create student
    private StudentService studentService;

    public StudentController(StudentService  studentService){
        this.studentService=studentService;
    }

    @PostMapping("/create")
    public StudentResponseDto createStudent(@Valid @RequestBody StudentRequestDto studentRequestDto){
        Student req = mapToEntityReq(studentRequestDto);
        Student res = studentService.createStudent(req);
        StudentResponseDto response = mapToDto(res);
        return response;
    }

    private StudentResponseDto mapToDto(Student res) {
        StudentResponseDto response = new StudentResponseDto();
        response.setAge(res.getAge());
        response.setEmail(res.getEmail());
        response.setId(res.getId());
        response.setName(res.getName());
        response.setRoll(res.getRoll());
        return response;
    }

    private Student mapToEntityReq(StudentRequestDto studentRequestDto) {
        Student entity = new Student();
        entity.setAge(studentRequestDto.getAge());
        entity.setEmail(studentRequestDto.getEmail());
        entity.setId(studentRequestDto.getId());
        entity.setName(studentRequestDto.getName());
        entity.setRoll(studentRequestDto.getRoll());
        return entity;
    }
    private Student mapToEntityRes(StudentResponseDto studentRequestDto) {
        Student entity = new Student();
        entity.setAge(studentRequestDto.getAge());
        entity.setEmail(studentRequestDto.getEmail());
        entity.setId(studentRequestDto.getId());
        entity.setName(studentRequestDto.getName());
        entity.setRoll(studentRequestDto.getRoll());
        return entity;
    }


    //read student
    @GetMapping("/get/{id}")
    public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Long id){
         Student response = studentService.getStudent(id);
         if(response==null)return  ResponseEntity.notFound().build();
             //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         return ResponseEntity.ok(mapToDto(response));
    }
 //get all
    @GetMapping("/getall")
    public ResponseEntity<List<StudentResponseDto>> getall(){
        List<Student> response = studentService.getall();
        if(response==null)return  ResponseEntity.notFound().build();
        List<StudentResponseDto> dtoResponse = new ArrayList<>();
        for(Student student : response){
            dtoResponse.add(mapToDto(student));
        }
        return ResponseEntity.ok(dtoResponse);
    }
    //update student
    @PutMapping("/updatestudent/{id}")
    public ResponseEntity<StudentResponseDto> update(@PathVariable Long id,@RequestBody StudentRequestDto studentReq){
        Student entity = mapToEntityReq(studentReq);
        Student response = studentService.update(id,entity);
        if(response==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapToDto(response));

    }

    //delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentResponseDto> delete(@PathVariable Long id){
        boolean res = studentService.delete(id);
        if(!res) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(null);
    }
}
