package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "ticket")
public class Ticket {
@Id
@GeneratedValue
    private int id;
@Column(name = "created_at")
    private LocalDateTime createdAt;
@Column(name = "client_id")
    private int clientId;
@Column(name = "from_planet_id")
    private int fromPlanetId;
@Column(name = "to_planet_id")
    private int toPlanetId;
}
