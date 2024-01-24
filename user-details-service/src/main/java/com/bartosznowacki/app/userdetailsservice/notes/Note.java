package com.bartosznowacki.app.userdetailsservice.notes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String value;
    @Column(name = "user_id")
    public Integer userId;
}
