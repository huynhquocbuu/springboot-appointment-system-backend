package appointment.backend.infrastructure.persistence.appointment.entities;

import appointment.backend.shared.enums.ERole;
import jakarta.persistence.*;


import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Enumerated(EnumType.STRING)
    private ERole id;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;
}
