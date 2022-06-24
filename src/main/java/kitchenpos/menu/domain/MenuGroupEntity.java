package kitchenpos.menu.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_group")
public class MenuGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    protected MenuGroupEntity() {
    }

    private MenuGroupEntity(String name) {
        this.name = new Name(name);
    }

    public static MenuGroupEntity of(String name) {
        return new MenuGroupEntity(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }
}
