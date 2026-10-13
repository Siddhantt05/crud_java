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

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto studentRequestDto){

        StudentResponseDto response = studentService.createStudent(studentRequestDto);

        return ResponseEntity.ok(response);
    }




    //read student
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Long id){
        StudentResponseDto response= studentService.getStudent(id);
         if(response==null)return  ResponseEntity.notFound().build();
             //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         return ResponseEntity.ok(response);
    }
 //get all
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getall(){
        List<StudentResponseDto> response = studentService.getall();
        if(response==null)return  ResponseEntity.notFound().build();

        return ResponseEntity.ok(response);
    }
    //update student
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> update(@PathVariable Long id,@RequestBody StudentRequestDto studentReq){

        StudentResponseDto response = studentService.update(id,studentReq);
        if(response==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);

    }

    //delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentResponseDto> delete(@PathVariable Long id){
        boolean res = studentService.delete(id);
        if(!res) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(null);
    }
}
