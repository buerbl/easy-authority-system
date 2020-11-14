package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author 布尔bl
 * @create 2020/11/14 21:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeStatuFlagDTO {

    private Integer status;

    private Integer Id;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
