package microproject.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microproject.user.enums.GenderType;
import microproject.user.enums.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String keycloakId;

    private String email;

    private String username;

    private String fullName;

    private String avatarUrl;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "gender_type")
    private GenderType gender;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "user_status")
    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoleList = new ArrayList<>();

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now();}
}
