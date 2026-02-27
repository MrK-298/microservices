package microproject.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_preferences")
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String language;

    private String timezone;

    private Boolean emailNotificationsEnabled;

    private Boolean pushNotificationsEnabled;

    private Boolean marketingEmailsEnabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
