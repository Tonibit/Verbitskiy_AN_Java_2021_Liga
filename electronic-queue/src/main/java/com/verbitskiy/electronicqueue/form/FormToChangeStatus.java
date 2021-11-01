package com.verbitskiy.electronicqueue.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FormToChangeStatus {

    private String username;

    private Long bookingId;

}
