package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.Planning;
import com.eleserv.qrCode.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UploadReposistory extends JpaRepository<Upload,Integer> {
    @Query(value ="select * from upload where case_id=:case_id",nativeQuery = true)
    List<Upload> getRecordByCaseStage(@Param("case_id") String case_id);
}
