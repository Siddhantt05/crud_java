package in.ProjectDemo.CrudDemo.repository;
import in.ProjectDemo.CrudDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Long> {

}
