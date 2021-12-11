package crud.spring.crudbackspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.spring.crudbackspring.model.Project;
import crud.spring.crudbackspring.repository.ProjetoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/back/spring")
public class ProjetoController {

    @Autowired
    private ProjetoRepository repository;
    
    
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProject(){
        try{
            List<Project> projets = repository.findAll();

            if(projets.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(projets,HttpStatus.OK);
            }

        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") long id){
        try {
            if(repository.findById(id).isPresent()){
                return new ResponseEntity<>(repository.findById(id).get(),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PostMapping("/createProject")
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        try{

            Project _project = repository.save(new Project(project.getName(),project.getDescription(),project.isPublished()));
            return new ResponseEntity<>(_project,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PutMapping("/updateProject/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") long id, @RequestBody Project project){
        try{
            
            Optional<Project> _obj = repository.findById(id);

            if(_obj.isPresent()){
                Project _project = _obj.get();
                _project.setName(project.getName());
                _project.setDescription(project.getDescription());	
                _project.setPublished(project.isPublished());
                return new ResponseEntity<>(repository.save(_project),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") long id){
        try{
            
            Optional<Project> _obj = repository.findById(id);

            if(_obj.isPresent()){
                repository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllProject")
    public ResponseEntity<HttpStatus> deleteAll(){
        try {
            repository.deleteAll();
            return new ResponseEntity<>(null,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
