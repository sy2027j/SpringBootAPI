package com.example.TestProject.Model.Entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass //부모 클래스를 상속받는 자식클래스에게 매핑 정보만 제공
public abstract class BaseEntity{

    @Column(name="create_time", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name="update_time", insertable = false, columnDefinition = "TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @PrePersist //before persist is called for a new entity 생성되기 전에
    protected void onCreate() {
        createTime = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate //before the update operation 업데이트 전에
    protected void onUpdate() {
        updateTime = Timestamp.valueOf(LocalDateTime.now());
    }

    public abstract Object getMember();
}
