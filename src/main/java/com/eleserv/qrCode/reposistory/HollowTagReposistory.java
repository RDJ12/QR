package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.HollowTag;
import com.eleserv.qrCode.entity.Stagging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HollowTagReposistory extends JpaRepository<HollowTag,Integer> {
    @Query(value ="select * from hollow_tagging where caseid=:caseid",nativeQuery = true)
    List<HollowTag> getHollowTaggingRecordById(@Param("caseid") String caseid);
}
