package appointment.backend.infrastructure.persistence.appointment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    private String id;
    private String fullName;
}
