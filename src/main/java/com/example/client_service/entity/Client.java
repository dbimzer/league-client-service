package com.example.client_service.entity;

import com.example.client_service.enums.ClientGender;
import com.example.client_service.enums.ClientStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "client_account")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    @NotNull
    @Size(max = 20)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)
    private ClientGender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private ClientStatus status = ClientStatus.ACTIVE;

    @Column(name = "create_dttm", nullable = false, updatable = false)
    private OffsetDateTime createDttm;

    @Column(name = "modify_dttm", nullable = false)
    private OffsetDateTime modifyDttm;

    @PrePersist
    protected void onCreate() {
        createDttm = OffsetDateTime.now();
        modifyDttm = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifyDttm = OffsetDateTime.now();
    }

}
