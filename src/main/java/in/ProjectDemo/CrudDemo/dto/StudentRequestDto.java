package in.ProjectDemo.CrudDemo.dto;

import jakarta.validation.constraints.*;

public class StudentRequestDto {
    @NotNull
    private Long id;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @Min(value = 15)
    private int age;
    @Email
    private String email;
    @NotNull
    private Integer roll;
    private String Subject;

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String subject;
}
