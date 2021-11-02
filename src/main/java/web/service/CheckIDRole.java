package web.service;

import org.springframework.stereotype.Service;

public class CheckIDRole {
    Boolean checked;
    Long id;
    String name;

    public CheckIDRole() {
    }

    public CheckIDRole(Boolean checked, Long id, String name) {
        this.checked = checked;
        this.id = id;
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
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
}
