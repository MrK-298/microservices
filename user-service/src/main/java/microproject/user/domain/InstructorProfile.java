package microproject.user.domain;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "instructor_profiles")
public class InstructorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String headline;

    private Integer yearsOfExperience;

    @Type(StringArrayType.class)
    @Column(name = "expertise_areas", columnDefinition = "_text")
    private String[] expertiseAreas;

    private String linkedinUrl;

    private String githubUrl;

    private String websiteUrl;

    private Boolean isVerified;

    private LocalDateTime verifiedAt;

    private Integer totalStudents;

    private Integer totalCourses;

    @Column(name = "average_rating", precision = 3, scale = 2)
    private BigDecimal averageRating;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
