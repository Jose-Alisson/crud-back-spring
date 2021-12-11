package crud.spring.crudbackspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROJETOS_ALISSON")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Project {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    public Project(String name, String description, boolean published) {
        this.name = name;
        this.description = description;
        this.published = published;
    }
}
