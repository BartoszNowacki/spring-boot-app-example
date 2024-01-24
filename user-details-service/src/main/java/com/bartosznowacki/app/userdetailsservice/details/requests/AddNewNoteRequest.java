package com.bartosznowacki.app.userdetailsservice.details.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddNewNoteRequest {
    private String note;
}
