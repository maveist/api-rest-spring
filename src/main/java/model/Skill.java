package model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by mmaheo on 16/03/2017.
 */
public class Skill {

    @Id
    private String id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    public Skill() {
        //do nothing
    }

    public Skill(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Skill[id=%s, name='%s']", id, name);
    }
}
