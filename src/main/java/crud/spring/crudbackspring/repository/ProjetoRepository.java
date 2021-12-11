package crud.spring.crudbackspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crud.spring.crudbackspring.model.Project;

public interface ProjetoRepository extends JpaRepository<Project,Long>{
    
}
