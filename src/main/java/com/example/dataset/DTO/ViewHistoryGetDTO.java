package com.example.dataset.DTO;

import lombok.Data;

@Data
public class ViewHistoryGetDTO {
    int user_id;
    int pageSize;
    int pageNum;
}
