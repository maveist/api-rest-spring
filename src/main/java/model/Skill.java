package model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;

/**
 * Created by mmaheo on 16/03/2017.
 */
public class Skill {

    @Id
    private String id;

    private HashMap<String, String> ressources;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    public Skill() {
        //do nothing
        ressources = new HashMap<>();
    }

    public Skill(String name) {
        this.name = name;
    }

    public String getId(){ return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Skill[id=%s, name='%s']", id, name);
    }

    public HashMap<String, String> getRessources(){return ressources;}

    public void addRessource(String label, String url){
        ressources.put(label, url);
    }

    public void deleteRessource(String label){
        ressources.remove(label);
    }

    public String getRessource(String label){
        return ressources.get(label);
    }
}
