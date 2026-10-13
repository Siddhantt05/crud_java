package in.ProjectDemo.CrudDemo.service;

import in.ProjectDemo.CrudDemo.entity.Student;
import in.ProjectDemo.CrudDemo.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public Student createStudent(Student student){
        //business logic
        //  store to db
       Student studentRes = studentRepository.save(student);

       return studentRes;
    }
    public Student getStudent(Long id){
        Optional<Student> response = studentRepository.findById(id);
        if(response.isPresent()) return response.get();
        return null;
    }

    public List<Student> getall(){
        List<Student> response = studentRepository.findAll();
        return response;
    }

    public Student update (Long id ,Student student){
        Optional<Student>  existing = studentRepository.findById(id);
        if(existing.isEmpty()) return null;
        Student studentToSave = existing.get();
        if(student.getAge()!=0) studentToSave.setAge(student.getAge());
        if(student.getEmail()!=null) studentToSave.setEmail(student.getEmail());
        if(student.getName()!=null) studentToSave.setName(student.getName());
        if(student.getSubject()!=null) studentToSave.setSubject(student.getSubject());
        if(student.getRoll()!=0) studentToSave.setRoll(student.getRoll());
        studentRepository.save(studentToSave);
        return studentToSave;
    }
    public boolean delete(Long id){
        Optional<Student> exist = studentRepository.findById(id);
        if(exist.isEmpty()) return false;
        studentRepository.delete(exist.get());
        return true;
    }
}
