package in.ProjectDemo.CrudDemo.service;

import in.ProjectDemo.CrudDemo.dto.StudentRequestDto;
import in.ProjectDemo.CrudDemo.dto.StudentResponseDto;
import in.ProjectDemo.CrudDemo.entity.Student;
import in.ProjectDemo.CrudDemo.exception.ResourceNotFoundException;
import in.ProjectDemo.CrudDemo.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto){
        //business logic
        //  store to db
      return  mapToDto(studentRepository.save(mapToEntityReq(studentRequestDto)));
    }
    public StudentResponseDto getStudent(Long id){
        Student response = studentRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("studetnt with id"+id+"not found"));
        return mapToDto(response);
    }

    public List<StudentResponseDto> getall(){
        List<Student> response = studentRepository.findAll();
        List<StudentResponseDto> responseDtos = new ArrayList<>();
        for(Student t : response) responseDtos.add(mapToDto(t));
        return responseDtos;
    }

    public StudentResponseDto update (Long id ,StudentRequestDto student){
        Optional<Student>  existing = studentRepository.findById(id);
        if(existing.isEmpty()) return null;
        Student studentToSave = existing.get();
        if(student.getAge()!=0) studentToSave.setAge(student.getAge());
        if(student.getEmail()!=null) studentToSave.setEmail(student.getEmail());
        if(student.getName()!=null) studentToSave.setName(student.getName());
        if(student.getSubject()!=null) studentToSave.setSubject(student.getSubject());
        if(student.getRoll()!=0) studentToSave.setRoll(student.getRoll());
        return mapToDto(studentRepository.save(studentToSave));

    }
    public boolean delete(Long id){
        Optional<Student> exist = studentRepository.findById(id);
        if(exist.isEmpty()) return false;
        studentRepository.delete(exist.get());
        return true;
    }
    private StudentResponseDto mapToDto(Student res) {
        StudentResponseDto response = new StudentResponseDto();
        response.setAge(res.getAge());
        response.setEmail(res.getEmail());
        response.setId(res.getId());
        response.setName(res.getName());
        response.setRoll(res.getRoll());
        response.setSubject(res.getSubject());
        return response;
    }

    private Student mapToEntityReq(StudentRequestDto studentRequestDto) {
        Student entity = new Student();
        entity.setAge(studentRequestDto.getAge());
        entity.setEmail(studentRequestDto.getEmail());
        entity.setId(studentRequestDto.getId());
        entity.setName(studentRequestDto.getName());
        entity.setRoll(studentRequestDto.getRoll());
        entity.setSubject(studentRequestDto.getSubject());
        entity.setSubject(studentRequestDto.getSubject());
        return entity;
    }
    private Student mapToEntityRes(StudentResponseDto studentResponseDto) {
        Student entity = new Student();
        entity.setAge(studentResponseDto.getAge());
        entity.setEmail(studentResponseDto.getEmail());
        entity.setId(studentResponseDto.getId());
        entity.setName(studentResponseDto.getName());
        entity.setRoll(studentResponseDto.getRoll());
        entity.setSubject(studentResponseDto.getSubject());
        return entity;
    }
}
